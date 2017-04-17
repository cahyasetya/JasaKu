<?php
// Routes

// $app->get('/', App\Action\HomeAction::class)
//     ->setName('homepage');

$app->group('', function(){
 	
 	//Route Table Pelanggan
    $this->group('/pelanggan', function(){
        $this->get('/all', 'App\Controller\PelangganController:getall');
        $this->get('/{id}', 'App\Controller\PelangganController:get');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu (Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\PelangganController:search');
        $this->post('/', 'App\Controller\PelangganController:create');
        $this->put('/', 'App\Controller\PelangganController:update');
        $this->delete('/{id}', 'App\Controller\PelangganController:delete');
    });
    //Route Table Penyedia Jasa
    $this->group('/jasa', function(){
        $this->get('/all', 'App\Controller\JasaController:getall');
        $this->get('/{id}', 'App\Controller\JasaController:get');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu (Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\PenyediaJasaController:search');
        $this->post('/', 'App\Controller\JasaController:create');
        $this->put('/', 'App\Controller\JasaController:update');
        $this->delete('/{id}', 'App\Controller\JasaController:delete');
    });
    //Route Paket
 	$this->group('/paket', function(){
        $this->get('/all', 'App\Controller\PaketController:getall');
        $this->get('/{id}', 'App\Controller\PaketController:get');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu (Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\PaketController:search');
        $this->post('/', 'App\Controller\PaketController:create');
        $this->put('/', 'App\Controller\PaketController:update');
        $this->delete('/{id}', 'App\Controller\PaketController:delete');
    });
    //Route Transaksi
    $this->group('/transaksi', function(){
        $this->get('/all', 'App\Controller\TransaksiController:getall');
        $this->get('/{id}', 'App\Controller\TransaksiController:get');
        //Proses search dilakukan pada semua kolom, BUKAN hanya 1 kolom tertentu(Tidak termasuk kolom primary key)
        $this->get('/search/{term}', 'App\Controller\TransaksiController:search');
        $this->post('/', 'App\Controller\TransaksiController:create');
        $this->put('/', 'App\Controller\TransaksiController:update');
        $this->delete('/{id}', 'App\Controller\TransaksiController:delete');
 
    });
});
// $app->get('/table', function($req, $res){
//     \App\Schema::createTables();
//     $res->write('All required table created');
//     return $res;
// });