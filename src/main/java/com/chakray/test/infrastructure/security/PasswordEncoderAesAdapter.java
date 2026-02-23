package com.chakray.test.infrastructure.security;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.chakray.test.domain.ports.out.PasswordEncoderPort;

@Primary
@Component
public class PasswordEncoderAesAdapter implements PasswordEncoderPort {
    @Value("${app.password-secret-key}")
    private String KEY_B64;

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;

    @Override
    public String encode(String rawPassword) {
        try {
            byte[] iv = new byte[IV_LENGTH_BYTE];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(KEY_B64), "AES");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            byte[] cipherText = cipher.doFinal(rawPassword.getBytes());

            ByteBuffer bb = ByteBuffer.allocate(iv.length + cipherText.length);
            bb.put(iv);
            bb.put(cipherText);

            return Base64.getEncoder().encodeToString(bb.array());
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar la contraseña con AES-256", e);
        }
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        try {
            String decryptedPassword = decrypt(encodedPassword);
            return rawPassword.equals(decryptedPassword);
        } catch (Exception e) {
            return false;
        }
    }

    private String decrypt(String encryptedTextWithIv) throws Exception {
        byte[] decode = Base64.getDecoder().decode(encryptedTextWithIv);
        ByteBuffer bb = ByteBuffer.wrap(decode);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(KEY_B64), "AES");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
        return new String(cipher.doFinal(cipherText));
    }
}
