[Dokumentasi Jasaku API](#jasaku-api)

##
Sebelum digunakan :

	1. Import file sql `jasaku.sql` dengan nama database jasaku
	2. Download file-filenya, dan taruh di apache folder `/htdocs/slim/Backend/`
	3. Nyalahkan apache dan mysql
	4. Siap Digunakan 
	
##

- [Register](#register)
	- [Daftar URL](#url)
	- [Parameters](#parameters)
	- [Contoh Respon](#respon)
- [Login](#login)
	- [Daftar URL](#url)
	- [Parameters](#parameters)
	- [Contoh Respon](#respon)
- [Membeli](#membeli)
	- [Daftar URL](#url)
	- [Parameters](#parameters)
	- [Contoh Respon](#respon)
- [Pengguna](#pengguna)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get Pesanan Masuk](#pesananmasuk)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Toko](#toko)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data dg id pengguna](#get_byidpengguna)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Buat Data](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Jasa](#jasa)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data dg id toko](#get_byidtoko)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Buat Data](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Transaksi](#transaksi)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data dg id pengguna](#get_byidpengguna)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Disetujui](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ditolak](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Kategori](#kategori)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Buat Data](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Kecamatan](#kecamatan)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data dg id kabupaten](#get_byidkabupaten)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Buat Data](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Kabupaten](#kabupaten)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data dg id provinsi](#get_byidprovinsi)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Buat Data](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
- [Provinsi](#provinsi)
	- [Get Semua Data](#getall)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Get 1 Data](#get)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Buat Data](#create)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Ubah Data](#update)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)
	- [Hapus Data](#delete)
		- [Daftar URL](#url)
		- [Parameters](#parameters)
		- [Contoh Respon](#respon)

## Jasaku API

### Register
Untuk melakukan pendaftaran bagi pengguna baru.

+ Menggunakan metode HTTP`POST`.

##### URL
+ [http://localhost/slim/Backend/src/public/register](). Dengan Parameter.

##### Parameters
+ `nama` **(wajib)**. 
+ `alamat` **(wajib)**.
+ `jenisKelamin` **(wajib)**.
+ `kontak` **(wajib)**. 
+ `username` **(wajib)**.
+ `password` **(wajib)**.
##### Respon Sukses
```json
{
  "status": "Sukses",
  "message": "Penambahan data berhasil"
}
```

+ Status Code `200`.
##### Respon Gagal (1)
Username telah digunakan

```json
{
  "status": "Gagal",
  "message": "Username sudah digunakan"
}
```

+ Status Code `400`.
##### Respon Gagal (2)
Error Sql, sbg contoh pengguna tdk menyertakan variabel username dan pasword

```json
{
  "status": "Gagal",
  "message": "Penambahan data gagal",
  "dev_message": "SQLSTATE[23000]: Integrity constraint violation: 1048 Column 'username' cannot be null (SQL: insert into `pengguna` (`id`, `nama`, `alamat`, `jenisKelamin`, `kontak`, `username`, `password`) values (5, Adik, Jl Dr Wahidin SH, P, 031567892, , ))"
}
```

+ Status Code `500`.

### Login
Untuk melakukan aktivitas sign in bagi pengguna yang sudah terdaftar

+ Menggunakan metode HTTP`POST`.

##### URL
+ [http://localhost/slim/Backend/src/public/login](). Dengan Parameter.

##### Parameters
+ `username` **(wajib)**.
+ `password` **(wajib)**.
##### Respon Sukses
```json
[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  }
]
```

+ Status Code `200`.
##### Respon Gagal (1)
Username telah digunakan

```json
{
  "status": "Gagal",
  "message": "Login Gagal"
}
```

+ Status Code `400`.
##### Respon Gagal (2)
Error Sql

```json
{
  "status": "Gagal",
  "message": "Login Gagal",
  "dev_message": "blablablablabl"
}
```

+ Status Code `500`.
