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

insert  into `pemesanan`(`kuantitas`,`total`,`id_jasa`,`id_transaksi`,`status_pemesanan`) values (1,10000,2,1,1),(1,25000,3,1,1),(1,10000,2,2,1),(1,35000,3,2,1);

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

insert  into `pengguna`(`id`,`nama`,`alamat`,`jenisKelamin`,`kontak`,`username`,`password`) values (1,'Amin','Jl Pahlawan Rt 2 No 1','L','08133546789','admin','admin'),(2,'Sueb','Jl Keputih 3 no 21','P','3987089','admin2','admin2');

/*Table structure for table `status_pemesanan` */

DROP TABLE IF EXISTS `status_pemesanan`;

CREATE TABLE `status_pemesanan` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `status_pemesanan` */

insert  into `status_pemesanan`(`id`,`nama`) values (1,'Menunggu Persetujuan Penjual'),(2,'Disetujui Penjual'),(3,'Ditolak Penjual'),(4,'Transaksi Sukses');

/*Table structure for table `toko` */

DROP TABLE IF EXISTS `toko`;

CREATE TABLE `toko` (
  `id` int(11) NOT NULL,
  `nama` varchar(9999) NOT NULL,
  `alamat` varchar(9999) NOT NULL,
  `kontak` varchar(20) NOT NULL,
  `deskripsi` varchar(9999) NOT NULL,
  `jamOperasional` varchar(9999) NOT NULL,
  `id_pengguna` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `toko_ibfk_1` (`id_pengguna`),
  CONSTRAINT `toko_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `toko` */

insert  into `toko`(`id`,`nama`,`alamat`,`kontak`,`deskripsi`,`jamOperasional`,`id_pengguna`) values (1,'Bengkel','Jl Keputih No 2 , Surabaya','0897865789','Menerima Spededa 4 tak dan matik','8.00-16.00',2),(2,'Laundry','Jl Mulyorejo No 1, Surabaya','3987654','Menerima Segala macam cucian','07.00-15.00',1),(3,'jahitin23','Jl Gebang timur no 27, Surabaya','089980789567','Menerima permak jeans, jahit seragam, memperbaiki tas,dll','07.00-17.00',2);

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

insert  into `transaksi`(`id`,`id_pengguna`,`total`,`updated_at`,`created_at`) values (1,2,45000,'2017-04-22','2017-04-22'),(2,1,45000,'2017-04-23','2017-04-23');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
