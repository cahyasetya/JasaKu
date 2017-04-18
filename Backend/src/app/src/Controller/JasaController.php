<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Jasa as Jasa;

final class JasaController {

    public function __construct(){}
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $jasa = new Jasa();

        $jasa->id_toko = $post['id_toko'];
        $jasa->nama = $post['nama'];
        $jasa->harga = $post['harga'];
        $jasa->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $jasas = Jasa::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($jasas));
        return $response;
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        $jasa = Jasa::find($args['id']);

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($jasa));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $jasas = Jasa::whereRaw('concat(id_toko," ",nama,"",harga) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($jasas));
        return $response;
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $jasa = Jasa::find($post['id']);


        if(isset($post['id_toko'])) $jasa->id_toko = $post['id_toko'];
        if(isset($post['nama'])) $jasa->nama = $post['nama'];
        if(isset($post['harga'])) $jasa->harga = $post['harga'];
        $jasa->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus jasa
    public function delete(Request $request, Response $response, $args){
        $jasa = Jasa::find($args['id']);
        $jasa->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}