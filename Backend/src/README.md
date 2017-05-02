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
+ [http://localhost/slim/Backend/src/public/register](). Tanpa Parameter.

##### Parameters

	*Tanpa Parameter*

##### Post Request Data
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
+ [http://localhost/slim/Backend/src/public/login](). Tanpa Parameter.

##### Parameters

*Tanpa Parameter*

##### Post Request Data
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
+ [http://localhost/slim/Backend/src/public/membeli](). Tanpa Parameter

##### Parameters

*Tidak ada Parameter*

##### Post Reuqst Data

```json
{
  "id_pengguna": $id,
  "paket":[
    {
        "kuantitas": $kuantitas,
        "id_jasa": $id_jasa
    },
    .
    .
    .......
    .......
    .......
    .
    .
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

	Merubah data pelanggan dengan id tertentu.Menggunakan metode HTTP `PUT`. Pada hedaer sertakan '''Content-Type'''dengan nilai '''application/x-www-form-urlencoded'''

	- URL
		- [http://localhost/slim/Backend/src/public/pengguna/](). Tanpa Parameter.

	- Parameter(x-www-form-urlencoded)
	
		*Tidak ada Parameter*
		
	- Put Request Data

		+ `id` **(wajib)**.
		+ `nama` *optional*. 
		+ `alamat` *optional*.
		+ `jenisKelamin` *optional*.
		+ `kontak` *optional*. 
		+ `username` *optional*.
		+ `password` *optional*.
		
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/pengguna/](). 
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
Semua operasi dengan tabel toko

- Get Semua Data
	
	Request semua data yang ada di tabel toko.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/toko/](). Tanpa Parameter.
	
		- [http://localhost/slim/Backend/src/public/toko/?id_kategori={id_kategori}&id_kecamatan={id_kecamatan}&sort={sort}](). Dengan Parameter.
	- Parameter
	
		- ```{id_kategori}``` *(optional)* bernilai angka
		- ```{id_kecamatan}```*(optional)* bernilai angka
		- ```{sort}```*(optional)* bernilai :
			- harga_asc (untuk sorting ascending berdasarkan harga)
			- harga_dsc (untuk sorting descending berdasarkan harga)
			- rating_asc (untuk sorting ascending berdasarkan rating)
			- rating_dsc (untuk sorting ascending berdasarkan harga)
			
	- Contoh Respon Sukses(1)
		- ```json
		[
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "jamOperasional": "8.00-16.00",
    "rating": 3,
    "id_pengguna": 2,
    "id_kategori": 6,
    "id_kecamatan": 16,
    "harga_terendah": 35000
  },
  {
    "id": 2,
    "nama": "Laundry",
    "alamat": "Jl Mulyorejo No 1, Surabaya",
    "kontak": "3987654",
    "deskripsi": "Menerima Segala macam cucian",
    "jamOperasional": "07.00-15.00",
    "rating": 3.5,
    "id_pengguna": 1,
    "id_kategori": 1,
    "id_kecamatan": 20,
    "harga_terendah": 10000
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "jamOperasional": "07.00-17.00",
    "rating": 4,
    "id_pengguna": 2,
    "id_kategori": 5,
    "id_kecamatan": 16,
    "harga_terendah": 5000
  }
]
		```
		
		- Request = [http://localhost/slim/Backend/src/public/toko/]()
		- Status Code `200`.
- Contoh Respon Sukses(2)
		- ```json
		[
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "jamOperasional": "07.00-17.00",
    "rating": 4,
    "id_pengguna": 2,
    "id_kategori": 5,
    "id_kecamatan": 16,
    "harga_terendah": 5000
  },
  {
    "id": 1,
    "nama": "Bengkel",
    "alamat": "Jl Keputih No 2 , Surabaya",
    "kontak": "0897865789",
    "deskripsi": "Menerima Spededa 4 tak dan matik",
    "jamOperasional": "8.00-16.00",
    "rating": 3,
    "id_pengguna": 2,
    "id_kategori": 6,
    "id_kecamatan": 16,
    "harga_terendah": 35000
  }
]
		```
		
		- Request = [http://localhost/slim/Backend/src/public/toko/?id_kategori=&id_kecamatan=16&sort=harga_asc]()
		- Status Code `200`.
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Penampilan Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data toko dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/toko/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 2,
  "nama": "Laundry",
  "alamat": "Jl Mulyorejo No 1, Surabaya",
  "kontak": "3987654",
  "deskripsi": "Menerima Segala macam cucian",
  "jamOperasional": "07.00-15.00",
  "rating": 3.5,
  "id_pengguna": 1,
  "id_kategori": 1,
  "id_kecamatan": 20,
  "harga_terendah": 10000
}
		```
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
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Penampilan Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get By Id Pengguna


	Request data toko dengan menyertakan ```id_pengguna``` pada argumen request.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/toko/id_pengguna/{id_pengguna}](). Tanpa Parameter.
			- ```{id_pengguna}``` berupa angka
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
    "jamOperasional": "8.00-16.00",
    "rating": 3,
    "id_pengguna": 2,
    "id_kategori": 6,
    "id_kecamatan": 16
  },
  {
    "id": 3,
    "nama": "jahitin23",
    "alamat": "Jl Gebang timur no 27, Surabaya",
    "kontak": "089980789567",
    "deskripsi": "Menerima permak jeans, jahit seragam, memperbaiki tas,dll",
    "jamOperasional": "07.00-17.00",
    "rating": 4,
    "id_pengguna": 2,
    "id_kategori": 5,
    "id_kecamatan": 16
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/toko/id_pengguna/2]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id_pengguna}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Toko Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/toko/id_pengguna/234]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Penampilan Toko Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Buat Data

	Mendaftarkan toko pada database tertentu.Menggunakan metode HTTP `POST`.

	- URL
		- [http://localhost/slim/Backend/src/public/toko/](). Tanpa Parameter.

	- Parameter
	
		*Tidak ada parameter*
	- Post Request Data

		+ `nama` **(wajib)**.
		+ `alamat` **(wajib)**. 
		+ `kontak` **(wajib)**.
		+ `deskripsi` **(wajib)**.
		+ `jamOperasional` **(wajib)**.
		+ `id_pengguna` **(wajib)**.
		+ `id_kategori` **(wajib)**. 
		+ `id_kecamatan` **(wajib)**.
		
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Penambahan data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/toko/](). 
		- Status Code `200`.
	
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Penambahan data gagal",
  "dev_message": "SQLSTATE[23000]: Integrity constraint violation: 1048 Column 'nama' cannot be null (SQL: insert into `toko` (`id`, `nama`, `alamat`, `kontak`, `deskripsi`, `jamOperasional`, `rating`, `id_pengguna`, `id_kategori`, `id_kecamatan`) values (5, , Jl Gebang timur no 27, Surabaya, 089980789567, Menerima permak jeans, jahit seragam, memperbaiki tas,dll, 07.00-17.00, 0, 2, 6, 16))"
}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data toko dengan id tertentu.Menggunakan metode HTTP `PUT`.Pada hedaer sertakan '''Content-Type'''dengan nilai '''application/x-www-form-urlencoded'''

	- URL
		- [http://localhost/slim/Backend/src/public/toko/](). Tanpa Parameter.

	- Parameter
	
		*Tidak Ada Parameter*
	- Put Request Data(x-www-form-urlencoded)

		+ `id` **(wajib)**.
		+ `nama`  *optional*.
		+ `alamat`  *optional*. 
		+ `kontak`  *optional*.
		+ `deskripsi`  *optional*.
		+ `jamOperasional`  *optional*.
		+ `id_pengguna`  *optional*.
		+ `id_kategori`  *optional*. 
		+ `id_kecamatan`  *optional*.
		+ `rating`  *optional*.
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/toko/](). 
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
		- Request : [http://localhost/slim/Backend/src/public/toko/]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Update Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data toko dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/toko/{id}](). Tidak ada Parameter.
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
		- Request : [http://localhost/slim/Backend/src/public/toko/3](). 
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
		- Request : [http://localhost/slim/Backend/src/public/pengguna/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Hapus Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Jasa
Semua operasi dengan tabel jasa

- Get Semua Data
	
	Request semua data yang ada di tabel jasa.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/jasa/](). Tanpa Parameter.
	
	- Parameter
	
		*Tanpa Parameter*.
			
	- Contoh Respon Sukses(1)
		- ```json
		[
  {
    "id": 1,
    "nama": "Cuci Kering",
    "harga": 20000,
    "id_toko": 2
  },
  {
    "id": 2,
    "nama": "Cuci Basah",
    "harga": 10000,
    "id_toko": 2
  },
  {
    "id": 3,
    "nama": "Ganti Oli",
    "harga": 35000,
    "id_toko": 1
  },
  {
    "id": 4,
    "nama": "Permak Jeans",
    "harga": 5000,
    "id_toko": 3
  }
]
		```
		
		- Request = [http://localhost/slim/Backend/src/public/jasa/]()
		- Status Code `200`.

	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Penampilan Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get 1 Data

	Request data jasa dengan id tertentu.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/jasa/{id}](). Tanpa Parameter.
			- ```{id}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		- ```json
		{
  "id": 2,
  "nama": "Cuci Basah",
  "harga": 10000,
  "id_toko": 2
}
		```
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Jasa Tidak ditemukan"
}
		```
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Penampilan Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Get By Id Toko


	Request data jasa dengan menyertakan ```id_toko``` pada argumen request.Menggunakan metode HTTP `GET`.
	- URL
		- [http://localhost/slim/Backend/src/public/jasa/id_toko/{id_toko}](). Tanpa Parameter.
			- ```{id_toko}``` berupa angka
	- Parameter
	
		*Tidak ada Parameter*
	- Contoh Respon Sukses
		
		- ```json
		[
  {
    "id": 1,
    "nama": "Cuci Kering",
    "harga": 20000,
    "id_toko": 2
  },
  {
    "id": 2,
    "nama": "Cuci Basah",
    "harga": 10000,
    "id_toko": 2
  }
]
		```
		- Request : [http://localhost/slim/Backend/src/public/toko/id_toko/2]()
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id_toko}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Jasa Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/jasa/id_toko/234]()
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Penampilan Jasa Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Buat Data

	Mendaftarkan jasa pada database .Menggunakan metode HTTP `POST`.

	- URL
		- [http://localhost/slim/Backend/src/public/jasa/](). Tanpa Parameter.

	- Parameter
	
		*Tidak Ada Parameter*
	- Post Request Data

		+ `id_toko` **(wajib)**.
		+ `nama` **(wajib)**. 
		+ `harga` **(wajib)**.
		
		
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Penambahan data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/jasa/](). 
		- Status Code `200`.
	
	- Contoh Respon Gagal
		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Penambahan data gagal",
  "dev_message": "SQLSTATE[23000]: Integrity constraint violation: 1048 Column 'id_toko' cannot be null (SQL: insert into `jasa` (`id`, `id_toko`, `nama`, `harga`) values (6, , dsfsdfsdf, 324324))"
}
		```
		- Status Code `500`.
- Ubah Data

	Merubah data jasa dengan id tertentu.Menggunakan metode HTTP `PUT`.
	Pada hedaer sertakan '''Content-Type'''dengan nilai '''application/x-www-form-urlencoded'''

	- URL
		- [http://localhost/slim/Backend/src/public/jasa/](). Tanpa Parameter.

	- Parameter
	
		*Tidak Ada Parameter*
	- Put Request Data(x-www-url-encoded)

		+ `id` **(wajib)**.
		+ `id_toko` **(optional)**.
		+ `nama` **(optional)**. 
		+ `harga` **(optional)**.
		
	- Contoh Respon Sukses
		
		- ```json
		{
  "status": "Sukses",
  "message": "Update data berhasil"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/jasa/](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Jasa Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/toko/]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Update Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.
- Hapus Data

	Menghapus data jasa dengan id tertentu.Menggunakan metode HTTP `DELETE`.

	- URL
		- [http://localhost/slim/Backend/src/public/jasa/{id}](). Tidak ada Parameter.
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
		- Request : [http://localhost/slim/Backend/src/public/jasa/3](). 
		- Status Code `200`.
	- Contoh Respon Gagal (1)
		
		```{id}``` yang dimasukkan tidak ditemukan		
		Error Sql
		- ```json
		{
  "status": "Gagal",
  "message": "Jasa Tidak ditemukan"
}
		```
		- Request : [http://localhost/slim/Backend/src/public/jasa/456]() 
		- Status Code `400`.
	- Contoh Respon Gagal (2)
		
		Error Sql
		- ```json
		{
			"status": "Gagal",
  			"message": "Hapus Data Gagal",
  			"dev_message": "blablablablabl"
  		}
		```
		- Status Code `500`.

### Transaksi

*Under Contruction*

### Kategori

*Under Contruction*

### Kecamatan

*Under Contruction*

### Kabupaten

*Under Contruction*

### Provinsi

*Under Contruction*