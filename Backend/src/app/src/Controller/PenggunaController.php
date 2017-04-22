<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Pengguna as Pengguna;

final class PenggunaController {

    public function __construct(){}
        
    //Tambah data
    public function create(Request $request, Response $response, $args){
        try{
            $post = $request->getParsedBody();

            $pengguna = new Pengguna();

            $pengguna->id=(Pengguna::all()->last()->id)+1;
            $pengguna->nama = $post['nama'];
            $pengguna->alamat = $post['alamat'];
            $pengguna->jenisKelamin = $post['jenisKelamin'];
            $pengguna->kontak = $post['kontak'];
            $pengguna->username = $post['username'];
            $pengguna->password = $post['password'];
        
            $pengguna->save();

            $response->write(json_encode([
            'status' => 'Sukses',
            'message'=> 'Penambahan data berhasil'
            
        ]));
            $status=200;
        }catch (\Illuminate\Database\QueryException $e){
            $response->write(json_encode([
            'status' => 'Gagal',
            'message'=> 'Penambahan data gagal'
            
        ]));
            $status=500;
        }

        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

//Login
    public function login(Request $request, Response $response, $args){
        $post = $request->getParsedBody();

        $username=$post['username'];
        $password=$post['password'];

        $response->withHeader('Content-type', 'application/json');
        if(Pengguna::whereName($username)->wherePassword($password)){
            $response->write(json_encode([
            'status' => 'sukses'
        ]));
        }else{
            $response->write(json_encode([
            'status' => 'failed'
        ]));
        }
        
        
        return $response;
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $penggunas = Pengguna::all();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($penggunas));
        return $response;
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        $pengguna = Pengguna::find($args['id']);
        var_dump($pengguna);
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($pengguna));
        return $response;
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        $penggunas = Pengguna::whereRaw('concat(nama," ",alamat,"",kontak,"",username,"",password) like ?', "%".$args['term']."%")->get();
        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode($penggunas));
        return $response;


    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        $post = $request->getParsedBody();
        $pengguna = Pengguna::find($post['id']);

        if(isset($post['nama'])) $pengguna->nama = $post['nama'];
        if(isset($post['alamat'])) $pengguna->alamat = $post['alamat'];
        if(isset($post['kontak'])) $pengguna->kontak = $post['kontak'];
        if(isset($post['jenisKelamin'])) $pengguna->jenisKelamin = $post['jenisKelamin'];
        if(isset($post['username'])) $pengguna->username = $post['username'];
        if(isset($post['password'])) $pengguna->password = $post['password'];
        $pengguna->save();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'sukses'
        ]));
        return $response;
    }

    //Hapus pengguna
    public function delete(Request $request, Response $response, $args){
        $pengguna = Pengguna::find($args['id']);
        $pengguna->delete();

        $response->withHeader('Content-type', 'application/json');
        $response->write(json_encode([
            'status' => 'sukses'
        ]));
        return $response;
    }
}