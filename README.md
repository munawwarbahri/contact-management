# 📒 Contact Management REST API

RESTful API untuk manajemen kontak pribadi yang dibangun dengan **Spring Boot**. API ini memungkinkan pengguna untuk mendaftarkan akun, mengelola kontak, dan menyimpan alamat tiap kontak secara terstruktur.

---

## 🚀 Teknologi yang Digunakan

| Teknologi | Keterangan |
|-----------|------------|
| Java | Bahasa pemrograman utama |
| Spring Boot | Framework backend |
| Spring Web | REST API |
| Spring Validation | Validasi request (Jakarta Validation) |
| Lombok | Mengurangi boilerplate code |
| Jackson | Serialisasi/deserialisasi JSON |

---

## ✨ Fitur

- **Autentikasi** — Register, login, dan logout dengan token-based auth
- **Manajemen User** — Update profil dan password
- **Manajemen Kontak** — CRUD kontak (nama, email, telepon) dengan fitur pencarian dan paginasi
- **Manajemen Alamat** — CRUD alamat yang terhubung ke tiap kontak

---

## 📁 Struktur Model

```
contactmanagement.restful.model
├── RegisterUserRequest.java      # Request registrasi user baru
├── LoginUserRequest.java         # Request login
├── UpdateUserRequest.java        # Request update profil user
├── TokenResponse.java            # Response token autentikasi
├── UserResponse.java             # Response data user
│
├── CreateContactRequest.java     # Request membuat kontak baru
├── UpdateContactRequest.java     # Request update kontak
├── SearchContactRequest.java     # Request pencarian kontak (dengan paginasi)
├── ContactResponse.java          # Response data kontak
│
├── CreateAddressRequest.java     # Request membuat alamat baru
├── UpdateAddressRequest.java     # Request update alamat
├── AddressResponse.java          # Response data alamat
│
├── WebResponse.java              # Wrapper response umum (data + errors + paging)
└── PagingResponse.java           # Informasi paginasi
```

---

## 🔌 API Endpoint

### Auth & User

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| `POST` | `/api/users` | Registrasi user baru |
| `POST` | `/api/auth/login` | Login dan mendapatkan token |
| `DELETE` | `/api/auth/logout` | Logout (hapus token) |
| `GET` | `/api/users/current` | Mendapatkan data user saat ini |
| `PATCH` | `/api/users/current` | Update data user |

### Contacts

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| `POST` | `/api/contacts` | Membuat kontak baru |
| `GET` | `/api/contacts/{contactId}` | Mendapatkan detail kontak |
| `PUT` | `/api/contacts/{contactId}` | Update kontak |
| `DELETE` | `/api/contacts/{contactId}` | Hapus kontak |
| `GET` | `/api/contacts?name=&email=&phone=&page=&size=` | Cari kontak dengan paginasi |

### Addresses

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| `POST` | `/api/contacts/{contactId}/addresses` | Tambah alamat ke kontak |
| `GET` | `/api/contacts/{contactId}/addresses/{addressId}` | Lihat detail alamat |
| `PUT` | `/api/contacts/{contactId}/addresses/{addressId}` | Update alamat |
| `DELETE` | `/api/contacts/{contactId}/addresses/{addressId}` | Hapus alamat |
| `GET` | `/api/contacts/{contactId}/addresses` | Daftar semua alamat kontak |

---

## 📦 Format Response

Semua endpoint mengembalikan response dalam format berikut:

```json
{
  "data": { ... },
  "errors": null,
  "paging": {
    "currentPage": 0,
    "totalPage": 5,
    "size": 10
  }
}
```

---

## 📝 Contoh Request & Response

### Register User
**POST** `/api/users`
```json
{
  "username": "johndoe",
  "password": "secret123",
  "name": "John Doe"
}
```

### Login
**POST** `/api/auth/login`
```json
{
  "username": "johndoe",
  "password": "secret123"
}
```
**Response:**
```json
{
  "data": {
    "token": "uuid-token-here",
    "expiredAt": 1700000000000
  },
  "errors": null,
  "paging": null
}
```

### Buat Kontak
**POST** `/api/contacts`
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "081234567890"
}
```

### Tambah Alamat ke Kontak
**POST** `/api/contacts/{contactId}/addresses`
```json
{
  "street": "Jl. Sudirman No. 1",
  "city": "Jakarta",
  "province": "DKI Jakarta",
  "country": "Indonesia",
  "postalCode": "10220"
}
```

---

## ⚙️ Cara Menjalankan

### Prasyarat
- Java 17+
- Maven atau Gradle
- Database (MySQL / PostgreSQL)

### Langkah Instalasi

1. Clone repository
   ```bash
   git clone https://github.com/munawwarbahri/contact-management.git
   cd contact-management
   ```

2. Konfigurasi database di `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/contact_management
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. Jalankan aplikasi
   ```bash
   ./mvnw spring-boot:run
   ```

4. API siap diakses di `http://localhost:8080`

---

## ✅ Validasi Field

| Field | Aturan |
|-------|--------|
| `username` | Wajib, maks. 100 karakter |
| `password` | Wajib, maks. 100 karakter |
| `firstName` | Wajib, maks. 100 karakter |
| `email` | Format email valid, maks. 100 karakter |
| `country` | Wajib, maks. 100 karakter |
| `postalCode` | Maks. 10 karakter |

---

## 📄 Lisensi

---

> Dibuat oleh [munawwarbahri](https://github.com/munawwarbahri)
