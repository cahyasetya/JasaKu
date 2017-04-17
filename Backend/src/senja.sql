/*
SQLyog Community v11.51 (32 bit)
MySQL - 10.1.9-MariaDB : Database - senja
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`senja` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `senja`;

/*Table structure for table `jasa` */

DROP TABLE IF EXISTS `jasa`;

CREATE TABLE `jasa` (
  `id` int(11) NOT NULL,
  `id_toko` int(11) NOT NULL,
  `nama` mediumtext,
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_penyedia_jasa` (`id_toko`),
  CONSTRAINT `jasa_ibfk_1` FOREIGN KEY (`id_toko`) REFERENCES `toko` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jasa` */

insert  into `jasa`(`id`,`id_toko`,`nama`,`harga`) values (0,2,'Ganti Oli',40000),(1,1,'Cuci Kering',14000),(2,1,'Cuci Setrika',17000),(3,1,'Cuci Basah',9000),(4,2,'Ganti Aki',200000),(5,1,'Cuci Selimut',30000);

/*Table structure for table `memesan` */

DROP TABLE IF EXISTS `memesan`;

CREATE TABLE `memesan` (
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_jasa` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `memesan` */

/*Table structure for table `pelanggan` */

DROP TABLE IF EXISTS `pelanggan`;

CREATE TABLE `pelanggan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(9999) DEFAULT NULL,
  `alamat` varchar(9999) DEFAULT NULL,
  `kelamin` char(1) DEFAULT NULL,
  `kontak` int(20) DEFAULT NULL,
  `username` varchar(256) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `pelanggan` */

insert  into `pelanggan`(`id`,`nama`,`alamat`,`kelamin`,`kontak`,`username`,`password`) values (1,'Testing','Jl TEsting',NULL,879324,NULL,NULL),(2,'tsting2','asd',NULL,234,NULL,NULL),(3,'postman_edit','Jl Google Chrome',NULL,24234,NULL,NULL),(5,'postman2','Jl Google Chrome',NULL,24234,NULL,NULL);

/*Table structure for table `toko` */

DROP TABLE IF EXISTS `toko`;

CREATE TABLE `toko` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pelanggan` int(11) DEFAULT NULL,
  `nama` varchar(9999) DEFAULT NULL,
  `alamat` varchar(9999) DEFAULT NULL,
  `kontak` int(20) DEFAULT NULL,
  `deskripsi` varchar(9999) DEFAULT NULL,
  `jam_operasional` varchar(9999) DEFAULT NULL,
  `foto` varchar(9999) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pengguna` (`id_pelanggan`),
  CONSTRAINT `toko_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `toko` */

insert  into `toko`(`id`,`id_pelanggan`,`nama`,`alamat`,`kontak`,`deskripsi`,`jam_operasional`,`foto`) values (1,1,'Laundry','Mulyosari no 95 Surabaya',2147483647,NULL,NULL,NULL),(2,2,'Bengkel','Keputih Gg 3 No 01 Surabaya',313987654,NULL,NULL,NULL);

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_jasa` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pelanggan` (`id_pelanggan`),
  KEY `id_paket` (`id_jasa`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id`),
  CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_jasa`) REFERENCES `jasa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `transaksi` */

insert  into `transaksi`(`id`,`id_pelanggan`,`id_jasa`,`jumlah`,`total_harga`,`updated_at`,`created_at`) values (0,1,2,3,51000,'2017-04-14 01:54:42','2017-04-14 01:54:40'),(2,2,4,1,20000,'2017-04-14 01:54:49','2017-04-14 01:54:45');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
