<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Pemesanan as Pemesanan;
use \App\Model\Statuspemesanan as Statuspemesanan;

final class PemesananController {

    public function __construct(){}
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $pemesanan = new Pemesanan();

        $pemesanan->id_jasa = $post['id_jasa'];
        $pemesanan->id_transaksi = $post['id_transaksi'];
        $pemesanan->id_pelanggan = $post['id_pelanggan'];
        $pemesanan->total = $post['total'];
        $pemesanan->kuantitas = $post['kuantitas'];
        $pemesanan->status_pemesanan = 1;
        $pemesanan->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $pemesanans = Pemesanan::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pemesanans));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $pemesanans = Pemesanan::whereRaw('concat(id_jasa," ",id_pelanggan,"",id_transaksi,"",total) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pemesanans));
        return $response;
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $pemesanan = Pemesanan::find($post['id']);


        if(isset($post['id_jasa'])) $pemesanan->id_jasa = $post['id_jasa'];
        if(isset($post['id_transaksi'])) $pemesanan->id_transaksi = $post['id_transaksi'];
        if(isset($post['id_pelanggan'])) $pemesanan->id_pelanggan = $post['id_pelanggan'];
        if(isset($post['total'])) $pemesanan->total = $post['total'];
        if(isset($post['kuantitas'])) $pemesanan->kuantitas = $post['kuantitas'];
        if(isset($post['status_pemesanan'])) $pemesanan->status_pemesanan = $post['status_pemesanan'];
        $pemesanan->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus pemesanan
    public function delete(Request $request, Response $response, $args){
        $pemesanan = Pemesanan::find($args['id_transaksi']);
        $pemesanan->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}