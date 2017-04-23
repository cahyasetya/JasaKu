<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Toko as Toko;

final class TokoController {

    public function __construct(){}
        
    //Tambah data
    public function create(Request $request, Response $response, $args){
        try{
            $post = $request->getParsedBody();

            $toko = new Toko();

            $toko->id = (Toko::all()->last()->id)+1;
            $toko->nama = $post['nama'];
            $toko->alamat = $post['alamat'];
            $toko->kontak = $post['kontak'];
            // $toko->foto = $post['foto'];
            $toko->deskripsi = $post['deskripsi'];
            $toko->id_pengguna = $post['id_pengguna'];
            $toko->jamOperasional = $post['jamOperasional'];
            $toko->save();

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

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        try{
            $tokos = Toko::all();
            $response->write(json_encode($tokos));
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
            $toko = Toko::find($args['id']);
            if(!$toko){
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Toko Tidak ditemukan'
                ]));
                $status=400;
            }else{
                $response->write(json_encode($toko));
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
            $tokos = Toko::whereRaw('concat(alamat," ",nama,"",kontak,"",deskripsi,"",id_pengguna,"",jamOperasional) like ?', "%".$args['term']."%")->get();
            $response->write(json_encode($tokos));
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
    //Update Toko
    public function update(Request $request, Response $response, $args){
        try{
            $post = $request->getParsedBody();

            $toko = Toko::find($post['id']);
            if(!$toko){
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Toko Tidak ditemukan'
                ]));
                $status=400;
            }else{
                if(isset($post['nama'])) $toko->nama = $post['nama'];
                if(isset($post['id_pengguna'])) $toko->id_pengguna = $post['id_pengguna'];
                if(isset($post['alamat'])) $toko->alamat = $post['alamat'];
                if(isset($post['kontak'])) $toko->kontak = $post['kontak'];
                // if(isset($post['foto'])) $toko->foto = $post['foto'];
                if(isset($post['deskripsi'])) $toko->deskripsi = $post['deskripsi'];
                if(isset($post['jamOperasional'])) $toko->jamOperasional = $post['jamOperasional'];
                $toko->save();

                $response->write(json_encode([
                    'status' => 'Sukses',
                    'message'=> 'Update data berhasil'
                ]));
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

    //Hapus toko
    public function delete(Request $request, Response $response, $args){
        try{
            $toko = Toko::find($args['id']);
            if(!$toko){
                $response->write(json_encode([
                    'status' => 'Gagal',
                    'message'=> 'Toko Tidak ditemukan'
                ]));
                $status=400;
            }else{
                $toko->delete();

                $response->write(json_encode([
                    'status' => 'Sukses',
                    'message'=> 'Hapus data berhasil'
                ]));
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
}