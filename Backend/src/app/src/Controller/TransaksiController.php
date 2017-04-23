<?php

namespace App\Controller;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;
use \App\Model\Transaksi as Transaksi;
use \App\Controller\JasaController as JasaController;
use \App\Controller\PemesananController as PemesananController;

final class TransaksiController {

    public function __construct(){}
        
    //Tambah data
    public function create(Request $request, Response $response, $args){
        try{
            $post = $request->getParsedBody();
            $array=json_decode(json_encode($post),true);

            $transaksi = new Transaksi();
            $transaksi->id = (Transaksi::all()->last()->id)+1;
            $temp_id=$transaksi->id;
            $transaksi->id_pengguna = $array['id_pengguna'];
            $transaksi->total = 0;
            $transaksi->save();

            $transaksi = Transaksi::find($temp_id);
            
            $total=0;

            $data_pemesanan=array();
            
            foreach($array['paket'] as $item){
                 $id= $item['id_jasa'];
                 $jasa = new JasaController();
                 $pemesanan = new PemesananController();
                 $jasa =json_decode($jasa->get_json($id),true);

                $data_pemesanan=array(array(
                    'kuantitas' => $item['kuantitas'],
                    'total'     =>  (int)$jasa['harga'],
                    'id_jasa'   => $item['id_jasa'],
                    'id_transaksi'  =>$transaksi->id,
                    'status_pemesanan'=>1
                ));
                 $pemesanan->create($data_pemesanan);

                 //Menghitung Total Transaksi
                 $total=$total+(int)$jasa['harga'];
            }
            $transaksi->total = $total;
            $transaksi->save();
            $response->write(json_encode([
                'status' => 'Sukses',
                'message'=> 'Pembelian Berhasil',
            ]));
            $status=200;
        }catch (\Illuminate\Database\QueryException $e){
            $response->write(json_encode([
                'status' => 'Gagal',
                'message'=> 'Pembelian gagal',
                'dev_message'=> $e->getMessage()
            ]));
            $status=500;
        }
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);
    }

	// Get semua data
    public function getall(Request $request, Response $response, $args){
        $transaksis = Transaksi::all();
        $pemesanan = new PemesananController();
        $data_pemesanan=array();
        foreach ($transaksis as $transaksi) {
            var_dump(json_decode($pemesanan->get_by_transaksi($transaksi["id"]),true));
        }
     
        //$response->write(json_encode($transaksis));
        $status=200;
        return $response->withHeader('Content-type', 'application/json')->withStatus($status);;
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