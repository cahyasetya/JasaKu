<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Memesan as Memesan;

final class MemesanController {

    public function __construct(){}
    //Tambah data
    public function create(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $memesan = new Memesan();

        $memesan->id_jasa = $post['id_jasa'];
        $memesan->id_transaksi = $post['id_transaksi'];
        $memesan->id_pelanggan = $post['id_pelanggan'];
        $memesan->jumlah = $post['jumlah'];
        $memesan->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $memesans = Memesan::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($memesans));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $memesans = Memesan::whereRaw('concat(id_jasa," ",id_pelanggan,"",id_transaksi,"",jumlah) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($memesans));
        return $response;
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $memesan = Memesan::find($post['id']);


        if(isset($post['id_jasa'])) $memesan->id_jasa = $post['id_jasa'];
        if(isset($post['id_transaksi'])) $memesan->id_transaksi = $post['id_transaksi'];
        if(isset($post['id_pelanggan'])) $memesan->id_pelanggan = $post['id_pelanggan'];
        if(isset($post['jumlah'])) $memesan->jumlah = $post['jumlah'];
        $memesan->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }

    //Hapus memesan
    public function delete(Request $request, Response $response, $args){
        $memesan = Memesan::find($args['id_transaksi']);
        $memesan->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'success'
        ]));
        return $response;
    }
}