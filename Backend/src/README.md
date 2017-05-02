[Dokumentasi Jasaku API](#jasaku-api)

##
Sebelum digunakan :

	1. Import file sql `jasaku.sql` dengan nama database jasaku
	2. Download file-filenya, dan taruh di apache folder `/htdocs/slim/Backend/`
	3. Nyalahkan apache dan mysql
	4. Siap Digunakan 
	
##

- [Register](#register)
- [Login](#login)
- [Membeli](#membeli)
- [Pengguna](#pengguna)
	- Get Semua Data
	- Get 1 Data
	- Get Pesanan Masuk
	- Ubah Data
	- Hapus Data
- [Toko](#toko)
	- Get Semua Data
	- Get 1 Data
	- Get 1 Data dg id pengguna
	- Buat Data
	- Ubah Data
	- Hapus Data
- [Jasa](#jasa)
	- Get Semua Data
	- Get 1 Data
	- Get 1 Data dg id toko
	- Buat Data
	- Ubah Data
	- Hapus Data
- [Transaksi](#transaksi)
	- Get Semua Data
	- Get 1 Data
	- Get 1 Data dg id pengguna
	- Hapus Data
	- Disetujui
	- Ditolak
- [Kategori](#kategori)
	- Get Semua Data
	- Get 1 Data
	- Buat Data
	- Ubah Data
	- Hapus Data
- [Kecamatan](#kecamatan)
	- Get Semua Data
	- Get 1 Data
	- Get 1 Data dg id kabupaten
	- Buat Data
	- Ubah Data
	- Hapus Data
- [Kabupaten](#kabupaten)
	- Get Semua Data
	- Get 1 Data
	- Get 1 Data dg id provinsi
	- Buat Data
	- Ubah Data
	- Hapus Data
- [Provinsi](#provinsi)
	- Get Semua Data
	- Get 1 Data
	- Buat Data
	- Ubah Data
	- Hapus Data
	
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
##### Contoh Respon Sukses
```json
{
  "status": "Sukses",
  "message": "Penambahan data berhasil"
}
```

+ Status Code `200`.
##### Contoh Respon Gagal (1)
Username telah digunakan

```json
{
  "status": "Gagal",
  "message": "Username sudah digunakan"
}
```

+ Status Code `400`.
##### Contoh Respon Gagal (2)
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
##### Contoh Respon Sukses
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
##### Contoh Respon Gagal (1)
Username telah digunakan

```json
{
  "status": "Gagal",
  "message": "Login Gagal"
}
```

+ Status Code `400`.
##### Contoh Respon Gagal (2)
Error Sql

```json
{
  "status": "Gagal",
  "message": "Login Gagal",
  "dev_message": "blablablablabl"
}
```

+ Status Code `500`.
+ 
### Membeli
Untuk melakukan Pembelian.

+ Menggunakan metode HTTP `POST`.

##### URL
+ [http://localhost/slim/Backend/src/public/membeli](). Dengan mengirimkan data json (detail pemesanan).

##### Parameters

*Tidak ada Parameter*

##### Format Data Yang Dikirim

```json
{
  "id_pengguna": $id,
  "paket":[
    {
        "kuantitas": $kuantitas,
        "id_jasa": $id_jasa
    },
    ......
    .......
    {
        "kuantitas": $kuantitas,
        "id_jasa": $id_jasa
    }
  ]
}
```
- Ket = ```"paket"``` sejumlah barang yang akan dipesan pengguna
- Dalam header sertakan ```Content-Type``` dengan isian ```application/json```
##### Contoh Respon Sukses
```json
{
  "status": "Sukses",
  "message": "Pembelian Berhasil"
}
```
+ Request : [http://localhost/slim/Backend/src/public/membeli](). Dengan data:

```json
{
  "id_pengguna": 1,
  "paket":[
    {
        "kuantitas": 8,
        "id_jasa": 1
    },
    {
        "kuantitas": 2,
        "id_jasa": 3
    }
  ]
}
```
+ Status Code `200`.
##### Contoh Respon Gagal 
Error Sql

```json
{
  "status": "Gagal",
  "message": "Penambahan data gagal",
  "dev_message": "blablabla"
}
```

+ Status Code `500`.
### Pengguna
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
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
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
```
		- Status Code `200`.

	- Contoh Respon Gagal
		
		Error Sql
		
```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
```

		- Status Code `500`.

- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.


### Toko
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Jasa
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Transaksi
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
### Kategori
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Kecamatan
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Kabupaten
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Provinsi
Semua operasi dengan tabel pengguna

- Get Semua Data
	
	Request semua data yang ada di tabel pelanggan.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna](). Tanpa Parameter.
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		[
  {
    "id": 1,
    "nama": "Amin",
    "alamat": "Jl Pahlawan Rt 2 No 1",
    "jenisKelamin": "L",
    "kontak": "08133546789",
    "username": "admin",
    "password": "admin"
  },
  {
    "id": 2,
    "nama": "Sueb",
    "alamat": "Jl Keputih 3 no 21",
    "jenisKelamin": "P",
    "kontak": "3987089",
    "username": "admin2",
    "password": "admin2"
  },
  {
    "id": 3,
    "nama": "Adik",
    "alamat": "Jl Dr Wahidin SH",
    "jenisKelamin": "P",
    "kontak": "031567892",
    "username": "admin3",
    "password": "admin"
  }]
		```
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data pelanggan dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 1,
  "nama": "Amin",
  "alamat": "Jl Pahlawan Rt 2 No 1",
  "jenisKelamin": "L",
  "kontak": "08133546789",
  "username": "admin",
  "password": "admin"
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get Pesanan Masuk


	Request data pesanan masuk pada **setiap toko** yang dimiliki pengguna.Pengguna hanya perlu input id nya.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}/pesananmasuk](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "id_pengguna": 2,
    "jamOperasional": "8.00-16.00",
    "pemesananmasuk": [
      {
        "kuantitas": 1,
        "total": 25000,
        "id_jasa": 3,
        "id_transaksi": 1,
        "status_pemesanan": 1
      },
      {
        "kuantitas": 2,
        "total": 35000,
        "id_jasa": 3,
        "id_transaksi": 3,
        "status_pemesanan": 1
      }
    ]
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "id_pengguna": 2,
    "jamOperasional": "07.00-17.00",
    "pemesananmasuk": []
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/2/pesananmasuk]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/25/pesananmasuk]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		+ 
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). Dengan argumen id dan nama
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/]() Dengan argumen id dan nama
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Dengan Parameter.

	- Parameter

			Menghapus data pelanggan dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/{id}](). Tidak ada Parameter.
			- ```{id}``` berupa angka

	- Parameter

		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Hapus data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/4](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Pengguna Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Login Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.