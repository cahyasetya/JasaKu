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
                'message'=> 'Penambahan data gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;
        }

        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

//Login
    public function login(Request $request, Response $response, $args){
        try{
            $post = $request->getParsedBody();
            $username=$post['username'];
            $password=$post['password'];
            $a=Pengguna::where([
                ['username', '=', $username],
                ['password', '=', $password]
            ])->get();
            
            if (json_decode($a)){
                $response->write(json_encode($a));
                //  $response->write(json_encode([
                //      'status' => 'Sukses',
                //      'message'=> 'Login Berhasil'
                //  ]));
                $status=200;    
            }else{
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Login Gagal'
                ]));
                $status=400;    
            }
        }catch (\Illuminate\Database\QueryException $e){
             $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Login Gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;    
        }
        
        
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        try{
            $penggunas = Pengguna::all();
            $response->write(json_encode($penggunas));
            $status=200;
        }catch (\Illuminate\Database\QueryException $e){
             $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Penambahan data gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;    
        }
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

    //Get 1 data
    public function get(Request $request, Response $response, $args){
        try{
            $pengguna = Pengguna::find($args['id']);
            if(!$pengguna){
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Pengguna Tidak ditemukan'
                ]));
                $status=400;
            }else{
                $response->write(json_encode($pengguna));
                $status=200;
            }
        }catch (\Illuminate\Database\QueryException $e){
             $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Penambahan data gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;    
        }
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

    //Cari data
    public function search(Request $request, Response $response, $args){
        try{
            $penggunas = Pengguna::whereRaw('concat(nama," ",alamat,"",kontak,"",username,"",password) like ?', "%".$args['term']."%")->get();
            $response->withHeader('Content-type', 'application/json');
            $response->write(json_encode($penggunas));
            $status=200;
        }catch (\Illuminate\Database\QueryException $e){
             $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Penambahan data gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;    
        }
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

    //Update Jasa
    public function update(Request $request, Response $response, $args){
        try{
            $post = $request->getParsedBody();
            $pengguna = Pengguna::find($post['id']);

            if(!$pengguna){
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Pengguna Tidak ditemukan'
                ]));
                $status=400;
            }else{
                if(isset($post['nama'])) $pengguna->nama = $post['nama'];
                if(isset($post['alamat'])) $pengguna->alamat = $post['alamat'];
                if(isset($post['kontak'])) $pengguna->kontak = $post['kontak'];
                if(isset($post['jenisKelamin'])) $pengguna->jenisKelamin = $post['jenisKelamin'];
                if(isset($post['username'])) $pengguna->username = $post['username'];
                if(isset($post['password'])) $pengguna->password = $post['password'];
                $pengguna->save();

                $response->write(json_encode([
                    'status' => 'Sukses',
                    'message'=> 'Update data berhasil'
                ]));
                $status=200;   
            }
        }catch (\Illuminate\Database\QueryException $e){
             $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Update data gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;    
        }
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

    //Hapus pengguna
    public function delete(Request $request, Response $response, $args){
        try{
            $pengguna = Pengguna::find($args['id']);
            if(!$pengguna){
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Pengguna Tidak ditemukan'
                ]));
                $status=400;
            }else{
                $pengguna->delete();

                $response->write(json_encode([
                    'status' => 'Sukses',
                    'message'=> 'Hapus data berhasil'
                ]));
                $status=200;
            }
        }catch (\Illuminate\Database\QueryException $e){
             $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Hapus data gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;    
        }
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }
}