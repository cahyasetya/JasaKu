<?php
// Routes

// $app->get('/', App\Action\HomeAction::class)
//     ->setName('homepage');
// 1. Pengguna
//     . register 
//     . login 
//     . kelola akun(update)
//     . kelolatoko
//         . lihat
//         . tambah
//         . hapus
//         . update
//         .
//     . kelolajasa
//     . membeli :
//         . masukkan ke keranjang
//         . beli

$app->group('', function(){
 	
 	//Route Table Pengguna
    $this->post('/register', 'App\Controller\PenggunaController:create');
    $this->post('/login', 'App\Controller\PenggunaController:login');    
    $this->group('/pengguna', function(){
        $this->get('[/]', 'App\Controller\PenggunaController:getall');
        $this->get('/{id}', 'App\Controller\PenggunaController:get');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu (Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\PenggunaController:search');
        $this->put('/', 'App\Controller\PenggunaController:update');
        $this->delete('/{id}', 'App\Controller\PenggunaController:delete');
    });


    //Route Table Toko
    $this->group('/toko', function(){
        $this->get('[/]', 'App\Controller\TokoController:getall');
        $this->get('/{id}', 'App\Controller\TokoController:get');
        $this->get('/id_pengguna/{id}', 'App\Controller\TokoController:get_byidpengguna');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu (Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\TokoController:search');
        $this->post('/', 'App\Controller\TokoController:create');
        $this->put('/', 'App\Controller\TokoController:update');
        $this->delete('/{id}', 'App\Controller\TokoController:delete');
    });
    //Route Toko
 	$this->group('/jasa', function(){
        $this->get('[/]', 'App\Controller\JasaController:getall');
        $this->get('/{id}', 'App\Controller\JasaController:get');
        $this->get('/id_toko/{id}', 'App\Controller\JasaController:get_byidtoko');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu (Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\JasaController:search');
        $this->post('/', 'App\Controller\JasaController:create');
        $this->put('/', 'App\Controller\JasaController:update');
        $this->delete('/{id}', 'App\Controller\JasaController:delete');
    });

    //Route Transaksi
    $this->post('/membeli', 'App\Controller\TransaksiController:create');
    $this->group('/transaksi', function(){
        $this->get('[/]', 'App\Controller\TransaksiController:getall');
        $this->get('/{id}', 'App\Controller\TransaksiController:get');
        $this->get('/id_pengguna/{id}', 'App\Controller\TransaksiController:get_byidpengguna');
        $this->delete('/{id}', 'App\Controller\TransaksiController:delete');
        $this->post('/disetujui', 'App\Controller\TransaksiController:disetujui');
        $this->post('/ditolak', 'App\Controller\TransaksiController:ditolak');
        $this->post('/sukses', 'App\Controller\TransaksiController:sukses');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu(Tidak termasuk kolom primary key)
        // $this->get('/search/{term}', 'App\Controller\TransaksiController:search');
        // $this->post('/', 'App\Controller\TransaksiController:create');
        // $this->put('/', 'App\Controller\TransaksiController:update');
        
 
    });

});
// $app->get('/table', function($req, $res){
//     \App\Schema::createTables();
//     $res->write('All required table created');
//     return $res;
// });


