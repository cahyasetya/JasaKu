/*
SQLyog Community v11.51 (32 bit)
MySQL - 10.1.9-MariaDB : Database - jasaku
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jasaku` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `jasaku`;

/*Table structure for table `jasa` */

DROP TABLE IF EXISTS `jasa`;

CREATE TABLE `jasa` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) NOT NULL,
  `harga` int(11) NOT NULL,
  `id_toko` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jasa_ibfk_1` (`id_toko`),
  CONSTRAINT `jasa_ibfk_1` FOREIGN KEY (`id_toko`) REFERENCES `toko` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jasa` */

insert  into `jasa`(`id`,`nama`,`harga`,`id_toko`) values (1,'Cuci Kering',20000,2),(2,'Cuci Basah',10000,2),(3,'Ganti Oli',35000,1),(4,'Permak Jeans',5000,3);

/*Table structure for table `kabupaten` */

DROP TABLE IF EXISTS `kabupaten`;

CREATE TABLE `kabupaten` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) DEFAULT NULL,
  `id_provinsi` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_provinsi` (`id_provinsi`),
  CONSTRAINT `kabupaten_ibfk_1` FOREIGN KEY (`id_provinsi`) REFERENCES `provinsi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kabupaten` */

insert  into `kabupaten`(`id`,`nama`,`id_provinsi`) values (1,'Surabaya',15),(2,'Gresik',15);

/*Table structure for table `kategori` */

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kategori` */

insert  into `kategori`(`id`,`nama`) values (1,'Laundry'),(2,'Makanan Ringan'),(3,'Makanan Berat'),(4,'Minuman'),(5,'Fashion'),(6,'Otomotif');

/*Table structure for table `kecamatan` */

DROP TABLE IF EXISTS `kecamatan`;

CREATE TABLE `kecamatan` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) DEFAULT NULL,
  `id_kabupaten` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kabupaten` (`id_kabupaten`),
  CONSTRAINT `kecamatan_ibfk_1` FOREIGN KEY (`id_kabupaten`) REFERENCES `kabupaten` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kecamatan` */

insert  into `kecamatan`(`id`,`nama`,`id_kabupaten`) values (1,'GENTENG',1),(2,'BUBUTAN',1),(3,'TEGALSARI',1),(4,'SIMOKERTO',1),(5,'TAMBAKSARI',1),(6,'GUBENG',1),(7,'KREMBANGAN',1),(8,'SEMAMPIR',1),(9,'PABEAN CANTIAN',1),(10,'WONOKROMO',1),(11,'SAWAHAN',1),(12,'TANDES',1),(13,'KARANG PILANG',1),(14,'WONOCOLO',1),(15,'RUNGKUT',1),(16,'SUKOLILO',1),(17,'KENJERAN',1),(18,'BENOWO',1),(19,'LAKARSANTRI',1),(20,'MULYOREJO',1),(21,'TENGGILIS MEJOYO',1),(22,'GUNUNG ANYAR',1),(23,'JAMBANGAN',1),(24,'GAYUNGAN',1),(25,'WIYUNG',1),(26,'DUKUH PAKIS',1),(27,'ASEMROWO',1),(28,'SUKO MANUNGGAL',1),(29,'BULAK',1),(30,'PAKAL',1),(31,'SAMBIKEREP',1),(32,'Balongpanggang',2),(33,'Benjeng',2),(34,'Bungah',2),(35,'Cerme',2),(36,'Driyorejo',2),(37,'Duduk Sampeyan',2),(38,'Dukun ',2),(39,'Gresik ',2),(40,'Kebomas ',2),(41,'Kedamean ',2),(42,'Manyar ',2),(43,'Menganti',2),(44,' Panceng ',2),(45,'Sangkapura ',2),(46,'Sidayu ',2),(47,'Tambak ',2),(48,'Ujung Pangkah ',2),(49,'Wringinanom',2);

/*Table structure for table `pemesanan` */

DROP TABLE IF EXISTS `pemesanan`;

CREATE TABLE `pemesanan` (
  `kuantitas` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `id_jasa` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `status_pemesanan` int(11) NOT NULL,
  KEY `kuantitas` (`kuantitas`),
  KEY `pemesanan_ibfk_4` (`status_pemesanan`),
  KEY `pemesanan_ibfk_1` (`id_jasa`),
  KEY `pemesanan_ibfk_3` (`id_transaksi`),
  CONSTRAINT `pemesanan_ibfk_1` FOREIGN KEY (`id_jasa`) REFERENCES `jasa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemesanan_ibfk_3` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemesanan_ibfk_4` FOREIGN KEY (`status_pemesanan`) REFERENCES `status_pemesanan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pemesanan` */

insert  into `pemesanan`(`kuantitas`,`total`,`id_jasa`,`id_transaksi`,`status_pemesanan`) values (1,10000,2,1,1),(1,25000,3,1,1),(3,20000,1,2,1),(2,20000,1,3,1),(2,35000,3,3,1);

/*Table structure for table `pengguna` */

DROP TABLE IF EXISTS `pengguna`;

CREATE TABLE `pengguna` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) NOT NULL,
  `alamat` varchar(9999) NOT NULL,
  `jenisKelamin` char(1) NOT NULL,
  `kontak` varchar(20) NOT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pengguna` */

insert  into `pengguna`(`id`,`nama`,`alamat`,`jenisKelamin`,`kontak`,`username`,`password`) values (1,'Amin','Jl Pahlawan Rt 2 No 1','L','08133546789','admin','admin'),(2,'Sueb','Jl Keputih 3 no 21','P','3987089','admin2','admin2'),(3,'Adik','Jl Dr Wahidin SH','P','031567892','admin3','admin');

/*Table structure for table `provinsi` */

DROP TABLE IF EXISTS `provinsi`;

CREATE TABLE `provinsi` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `provinsi` */

insert  into `provinsi`(`id`,`nama`) values (1,'Aceh'),(2,'Sumatera Utara'),(3,'Bengkulu'),(4,'Jambi'),(5,'Riau'),(6,'Sumatera Barat'),(7,'Sumatera Selatan'),(8,'Lampung'),(9,'Kepulauan Bangka Belitung'),(10,'Kepulauan Riau'),(11,'Banten'),(12,'Jawa Barat'),(13,'DKI Jakarta'),(14,'Jawa Tengah'),(15,'Jawa Timur'),(16,'Daerah Istimewa Yogyakarta'),(17,'Bali'),(18,'Nusa Tenggara Barat'),(19,'Nusa Tenggara Timur'),(20,'Kalimantan Barat'),(21,'Kalimantan Selatan'),(22,'Kalimantan Tengah'),(23,'Kalimantan Timur'),(24,'Gorontalo'),(25,'Sulawesi Selatan'),(26,'Sulawesi Tenggara'),(27,'Sulawesi Tengah'),(28,'Sulawesi Utara'),(29,'Sulawesi Barat'),(30,'Maluku'),(31,'Maluku Utara'),(32,'Papua'),(33,'Papua Barat');

/*Table structure for table `status_pemesanan` */

DROP TABLE IF EXISTS `status_pemesanan`;

CREATE TABLE `status_pemesanan` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `status_pemesanan` */

insert  into `status_pemesanan`(`id`,`nama`) values (1,'Menunggu Persetujuan Penjual'),(2,'Disetujui Penjual'),(3,'Ditolak Penjual');

/*Table structure for table `toko` */

DROP TABLE IF EXISTS `toko`;

CREATE TABLE `toko` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) NOT NULL,
  `alamat` varchar(9999) NOT NULL,
  `kontak` varchar(20) NOT NULL,
  `deskripsi` varchar(9999) NOT NULL,
  `jamOperasional` varchar(9999) NOT NULL,
  `rating` float DEFAULT NULL,
  `id_pengguna` int(11) NOT NULL,
  `id_kategori` int(11) DEFAULT NULL,
  `id_kecamatan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `toko_ibfk_1` (`id_pengguna`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_kecamatan` (`id_kecamatan`),
  CONSTRAINT `toko_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `toko_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `toko_ibfk_3` FOREIGN KEY (`id_kecamatan`) REFERENCES `kecamatan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `toko` */

insert  into `toko`(`id`,`nama`,`alamat`,`kontak`,`deskripsi`,`jamOperasional`,`rating`,`id_pengguna`,`id_kategori`,`id_kecamatan`) values (1,'Bengkel','Jl Keputih No 2 , Surabaya','0897865789','Menerima Spededa 4 tak dan matik','8.00-16.00',3,2,6,16),(2,'Laundry','Jl Mulyorejo No 1, Surabaya','3987654','Menerima Segala macam cucian','07.00-15.00',3.5,1,1,20),(3,'jahitin23','Jl Gebang timur no 27, Surabaya','089980789567','Menerima permak jeans, jahit seragam, memperbaiki tas,dll','07.00-17.00',4,2,5,16);

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `id_pengguna` int(11) DEFAULT NULL,
  `total` int(11) NOT NULL,
  `updated_at` date NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pelanggan` (`id_pengguna`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `transaksi` */

insert  into `transaksi`(`id`,`id_pengguna`,`total`,`updated_at`,`created_at`) values (1,2,45000,'2017-04-22','2017-04-22'),(2,1,200000,'2017-04-24','2017-04-24'),(3,1,110000,'2017-04-25','2017-04-25');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
