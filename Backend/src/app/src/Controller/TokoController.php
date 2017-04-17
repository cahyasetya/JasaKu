<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Toko as Toko;

final class TokoController {

    public function __construct(){}
        
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $toko = new Toko();

        $toko->nama = $post['nama'];
        $toko->alamat = $post['alamat'];
        $toko->foto = $post['foto'];
        $toko->kontak = $post['kontak'];
        $toko->deskripsi = $post['deskripsi'];
        $toko->id_pelanggan = $post['id_pelanggan'];
        $toko->jam_operasional = $post['jam_operasional'];
        $toko->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $tokos = Toko::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($tokos));
        return $response;
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        $toko = Toko::find($args['id']);

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($toko));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $tokos = Toko::whereRaw('concat(alamat," ",nama,"",kontak,"",deskripsi,"",id_pelanggan,"",jam_operasional) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($tokos));
        return $response;
    }
    //Update Toko
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $toko = Toko::find($post['id']);

        if(isset($post['nama'])) $toko->nama = $post['nama'];
        if(isset($post['id_pelanggan'])) $toko->id_pelanggan = $post['id_pelanggan'];
        if(isset($post['alamat'])) $toko->alamat = $post['alamat'];
        if(isset($post['kontak'])) $toko->kontak = $post['kontak'];
        if(isset($post['foto'])) $toko->foto = $post['foto'];
        if(isset($post['deskripsi'])) $toko->deskripsi = $post['deskripsi'];
        if(isset($post['jam_operasional'])) $toko->jam_operasional = $post['jam_operasional'];
        $toko->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus toko
    public function delete(Request $request, Response $response, $args){
        $toko = Toko::find($args['id']);
        $toko->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}