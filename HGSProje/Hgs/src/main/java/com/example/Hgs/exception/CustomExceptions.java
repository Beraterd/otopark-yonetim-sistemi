package com.example.Hgs.exception;

public class CustomExceptions {

    // Kullanıcı bulunamadı hatası
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    // Gişe bulunamadı hatası
    public static class GiseNotFoundException extends RuntimeException {
        public GiseNotFoundException(String message) {
            super(message);
        }
    }

    // Araç sahibi bulunamadı hatası
    public static class AracSahibiNotFoundException extends RuntimeException {
        public AracSahibiNotFoundException(String message) {
            super(message);
        }
    }

    // Kullanıcı zaten mevcut hatası
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    // Şifre hatalı hatası
    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    // Geçersiz bakiye ekleme hatası
    public static class InvalidAmountException extends RuntimeException {
        public InvalidAmountException(String message) {
            super(message);
        }
    }
}

