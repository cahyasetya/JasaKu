<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Transaksi as Transaksi;

final class TransaksiController {

    public function __construct(){}
        
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $transaksi = new Transaksi();

        // $transaksi->id_pelanggan = $post['id_pelanggan'];
        // $transaksi->id_paket = $post['id_paket'];
        $transaksi->id = Transaksi::all()->last()->id;
        $transaksi->total = $post['total'];
        $transaksi->updated_at = $post['updated_at'];
        $transaksi->created_at = $post['created_at'];
        $transaksi->save();


        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $transaksis = Transaksi::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($transaksis));
        return $response;
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        $transaksi = Transaksi::find($args['id']);

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($transaksi));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $transaksis = Transaksi::whereRaw('concat(total,"",updated_at,"",created_at) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($transaksis));
        return $response;
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $transaksi = Transaksi::find($post['id']);

        // if(isset($post['id_pelanggan'])) $transaksi->id_pelanggan = $post['id_pelanggan'];
        // if(isset($post['id_paket'])) $transaksi->id_paket = $post['id_paket'];
        if(isset($post['total'])) $transaksi->total = $post['total'];
        if(isset($post['updated_at'])) $transaksi->no_telepon = $post['updated_at'];
        if(isset($post['created_at'])) $transaksi->no_telepon = $post['created_at'];
        $transaksi->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus transaksi
    public function delete(Request $request, Response $response, $args){
        $transaksi = Transaksi::find($args['id']);
        $transaksi->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}