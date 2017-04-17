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

        $jasa->nama = $post['nama'];
        $jasa->id_pelanggan = $post['id_pelanggan'];
        $jasa->alamat = $post['alamat'];
        $jasa->no_telepon = $post['no_telepon'];
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
        $jasas = Jasa::whereRaw('concat(id_pelanggan," ",nama,"",alamat,"",no_telepon) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($jasas));
        return $response;
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $jasa = Jasa::find($post['id']);

        if(isset($post['nama'])) $jasa->nama = $post['nama'];
        if(isset($post['id_pelanggan'])) $jasa->id_pelanggan = $post['id_pelanggan'];
        if(isset($post['alamat'])) $jasa->alamat = $post['alamat'];
        if(isset($post['no_telepon'])) $jasa->no_telepon = $post['no_telepon'];
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