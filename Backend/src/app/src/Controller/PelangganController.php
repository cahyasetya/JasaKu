<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Pelanggan as Pelanggan;

final class PelangganController {

    public function __construct(){}
        
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $pelanggan = new Pelanggan();

        $pelanggan->nama = $post['nama'];
        $pelanggan->alamat = $post['alamat'];
        $pelanggan->no_telepon = $post['no_telepon'];
        $pelanggan->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $pelanggans = Pelanggan::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pelanggans));
        return $response;
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        $pelanggan = Pelanggan::find($args['id']);
        var_dump($pelanggan);
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pelanggan));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $pelanggans = Pelanggan::whereRaw('concat(nama," ",alamat,"",no_telepon) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pelanggans));
        return $response;


    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

//        var_dump($cuk);

        $pelanggan = Pelanggan::find($post['id']);

        if(isset($post['nama'])) $pelanggan->nama = $post['nama'];
        if(isset($post['alamat'])) $pelanggan->alamat = $post['alamat'];
        if(isset($post['no_telepon'])) $pelanggan->no_telepon = $post['no_telepon'];
        $pelanggan->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus pelanggan
    public function delete(Request $request, Response $response, $args){
        $pelanggan = Pelanggan::find($args['id']);
        $pelanggan->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}