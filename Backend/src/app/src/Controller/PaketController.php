<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Paket as Paket;

final class PaketController {

    public function __construct(){}
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $paket = new Paket();

        $paket->id_jasa = $post['id_jasa'];
        $paket->nama = $post['nama'];
        $paket->deskripsi = $post['deskripsi'];
        $paket->harga = $post['harga'];
        $paket->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $pakets = Paket::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pakets));
        return $response;
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        $paket = Paket::find($args['id']);

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($paket));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $pakets = Paket::whereRaw('concat(id_jasa," ",deskripsi,"",nama,"",harga) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pakets));
        return $response;
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $paket = Paket::find($post['id']);


        if(isset($post['id_jasa'])) $paket->id_jasa = $post['id_jasa'];
        if(isset($post['nama'])) $paket->nama = $post['nama'];
        if(isset($post['deskripsi'])) $paket->deskripsi = $post['deskripsi'];
        if(isset($post['harga'])) $paket->harga = $post['harga'];
        $paket->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus paket
    public function delete(Request $request, Response $response, $args){
        $paket = Paket::find($args['id']);
        $paket->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}