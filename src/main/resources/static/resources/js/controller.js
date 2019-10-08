/*global angular, isNumber, showMsgError, showMsgSuccess, startLoad, finishLoad, console, errCallbackService*/
var app = angular.module('app', ['ngResource', 'ngRoute', 'ngAnimate']);


app.service("Configuration", function() {
	  if (window.location.host.match(/herokuapp\.com/)) {
	    return this.API = 'http://teste-softplan.herokuapp.com/api/teste-softplan';
	  } else {
	    return this.API = 'http://localhost\\:8080/api/teste-softplan';
	  }
	});


/**
 * Controller para view de pesquisade persons
 * ações de busca redirecionamento etc estão contidas neste bloco
 * */
app.controller('pesquisarPersonCtrl', function ($scope, PersonResource ,$timeout) {
    $scope.view = 'Pesquisar Pessoa';
    //direciona para pagina de inclusão
    $scope.newPerson = function () {
        location.href = '#/include-person';
    };
    $scope.editPerson = function (id) {
        location.href = '#/edit-person/' + id;
    };
    
    //funçao para a busca de persons
    function getPerson(nome) {
        if (isNumber(nome)) {
            showMsgError($scope, $timeout, 'Digite uma cadeia válida de caracteres!!!');
            return;
        } else if (nome == null) {
            startLoad();
            $scope.persons = PersonResource.getAll(function (data) {
                console.log(data);
                $scope.isLoaded = true;
                finishLoad();
            }, function (err) {
                console.log(err);
                errCallbackService(err, $scope, $timeout);
                $scope.isLoaded = false;
                finishLoad();
            });							
        } else {
            startLoad();
            $scope.persons = PersonResource.query({nome : nome}, function (data) {
                $scope.isLoaded = true;
                finishLoad();
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
                $scope.isLoaded = false;
                finishLoad();
            });							
        }
    }
    
    //chama a função para a busca de persons de acordo com o campo de busca
    $scope.getPerson = function () {
        if (typeof $scope.person == 'undefined') {
            getPerson(null);
        } else {
            getPerson($scope.person.nome);
        }
    };

    /*$scope.removePerson = function (idParam, index) {
        personResource.remove({id : idParam}, function (data) {
            $scope.persons.splice(index, 1);
            showMsgSuccess($scope, $timeout, 'Person excluido com sucesso!!!');
        }, function (err) {
            console.log(err);
            errCallbackService(err, $scope, $timeout);
        });
    }*/

    
});	
	
/**
 * Controller view incluir Person
 * **/
app.controller('incluirAlterarPersonCtrl', function ($scope, PersonResource, buscaCepResource, $timeout, $routeParams) {
    console.log(location.hash)
    /*Função para carregar as funções e atributos da view de alteração ou criação*/
    function carregaView() {
        if (location.hash == '#/include-person') {
            $scope.acaoButton = 'Incluir Pessoa';
            $scope.person = {genero : 'M'};
            return 'Incluir Person';
        } else if (location.hash.search('edit-person') > -1) {
            $scope.acaoButton = 'Alterar Pessoa';
            $scope.person  = PersonResource.get({id : $routeParams.id}, function (data) {
                console.log($scope.person);					
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
                //console.log(err);
            });
            return 'Editar Person';
        }
    }
    //nome da view de acordo coma requisição
    $scope.view = carregaView();		
    //carregamento das páginas
    $scope.isLoaded = false;		
    

    $scope.savePerson = function () {
        if(isNumber($scope.person.id) && location.hash.search('edit-person') > -1) {
            startLoad();
            PersonResource.update($scope.person, function (data) {
                showMsgSuccess($scope, $timeout, 'Person Alterado com Sucesso!!!');
                $scope.person = data;
                console.log(data);
                finishLoad();
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
                finishLoad();
            });
        }
        else {
            startLoad();
            console.log($scope.person);
            console.log($scope.genero);
            PersonResource.save($scope.person, function (data) {
                showMsgSuccess($scope, $timeout, 'Person Inserido com Sucesso!!!');
                $scope.person = data;
                console.log(data);
                finishLoad();
            },function (err) {
                errCallbackService(err, $scope, $timeout);
                finishLoad();
            });				
        }
    }	

    //buscar endereço pelo cep
    $scope.buscaEndereco = function () {
        if(typeof $scope.person == 'undefined' || typeof $scope.person.endereco == 'undefined') {
            showMsgError($scope, $timeout, 'Informe o CEP');
            angular.element('#cep').focus();
            return;
        }
        startLoad();;
        buscaCepResource.get({cep : $scope.person.endereco.endeCep, formato : 'json'}, function (data) {
            if(data.resultado_txt == 'sucesso - cep não encontrado') {
                finishLoad();
                showMsgError($scope, $timeout, 'Cep Inexisteste!!!');
                return;
            }
            $scope.person.endereco = {endeCep : $scope.person.endereco.endeCep, endeRua : data.logradouro, endeBairro : data.bairro,  cidade : {descr : data.cidade, estado : {sigla : data.uf}}};
            finishLoad();
        },function (err) {
            finishLoad();
            showMsgError($scope, $timeout, 'Erro ao Buscar Cep!!!');
            console.log(data);
        });	
    }		
});


/*app.controller('ListaComprasController', function ($scope, usuarioResource ) {
    $scope.isLoaded = false;
    $scope.itens = usuarioResource.query(function (data) {
        console.log(data);
        $scope.isLoaded = true;
    });

    [
        {id : 23, produto: 'Leite', quantidade: 2, comprado: false},
        {id : 24, produto: 'Cerveja', quantidade: 12, comprado: false}
    ];



    $scope.adicionaItem = function () {
        var key = $scope.itens.length >= 1  ? ($scope.itens[$scope.itens.length-1].id + 1) : 1;
        $scope.itens.push({id : key,  produto: $scope.item.produto,
                           quantidade: $scope.item.quantidade,
                           comprado: false});
        $scope.item.produto = $scope.item.quantidade = '';
    };

    $scope.remove = function ( idx ) {        	
        var item_to_delete = $scope.itens[idx];
        $scope.itens.splice(idx, 1);
    };
});
*/	
app.controller('formLogin', function ($scope, $http) {
    //var authdata = Base64.encode($scope.user.name + ':' + $scope.user.passwd);
    //$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
    $scope.submitLogin = function () {
        $http({
            url: 'rest/usuario',
            data: $scope.user,
            method: 'POST',
            headers : {'Content-Type':'application/json; charset=UTF-8'}

        }).success(function (data) {
            console.log('OK', data);
        }).error(function (err) {'ERR', console.log(err)})
    };

});

app.controller('orcamentoCtrl', function ($scope, $location, $filter) {
    $scope.message = 'Orçamento';

    
    $scope.newOrcamento = function(){
        location.href = '#incluir-orcamento'
    }
});

app.controller('homeCtrl', function ($scope) {
    $scope.message = 'Heloo Home';
});

angular.element(function() {
    angular.bootstrap(document, ['app']);
});