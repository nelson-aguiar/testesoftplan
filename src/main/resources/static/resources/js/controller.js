/*global angular, isNumber, showMsgError, showMsgSuccess, startLoad, finishLoad, console, errCallbackService*/
var app = angular.module('app', ['ngResource', 'ngRoute', 'ngAnimate']);


app.service("Configuration", function() {
	this.customHeaderV1 = {"Accept" : "application/vnd.nelsonaguiar.testesoftplan-v1+json", "Content-Type" : "application/vnd.nelsonaguiar.testesoftplan-v1+json"};
	this.customHeaderV2 = {"Accept" : "application/vnd.nelsonaguiar.testesoftplan-v2+json", "Content-Type" : "application/vnd.nelsonaguiar.testesoftplan-v2+json"};
	if (window.location.host.match(/herokuapp\.com/)) {
		this.API = 'http://teste-softplan.herokuapp.com/api/teste-softplan';
		return this;
	} else {
		this.API = 'http://localhost\\:8080/api/teste-softplan';
		return this;
	}
});


/**
 * Controller para view de pesquisade persons
 * ações de busca redirecionamento etc estão contidas neste bloco
 * */
app.controller('pesquisarPersonCtrl', function ($scope, PersonResource ,$timeout, Configuration) {
    $scope.view = 'Pesquisar Pessoa';
    //direciona para pagina de inclusão
    $scope.newPerson = function () {
        location.href = '#/v1/include-person';
    };
    $scope.editPerson = function (id) {
    	console.log(id);
        location.href = '#/v1/edit-person/' + id;
    };
    
    //funçao para a busca de persons
    function getPerson(nome) {
        if (isNumber(nome)) {
            showMsgError($scope, $timeout, 'Digite uma cadeia válida de caracteres!!!');
            return;
        } else if (nome == null) {
            startLoad();
            $scope.persons = PersonResource(Configuration.customHeaderV1).getAll(function (data) {
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
            $scope.persons = PersonResource(Configuration.customHeaderV1).query({name : nome}, function (data) {
                $scope.isLoaded = true;
                finishLoad();
            }, function (err) {
                console.log(err);
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

    $scope.removePerson = function (idParam, index) {
    	PersonResource(Configuration.customHeaderV1).remove({id : idParam}, function (data) {
            $scope.persons.splice(index, 1);
            showMsgSuccess($scope, $timeout, 'Pessoa excluida com sucesso!!!');
        }, function (err) {
            console.log(err);
            errCallbackService(err, $scope, $timeout);
        });
    }    
});	
	
/**
 * Controller view incluir Person V1
 * **/
app.controller('incluirAlterarPersonCtrl', function ($scope, PersonResource, buscaCepResource, $timeout, $routeParams, Configuration) {
    /*Função para carregar as funções e atributos da view de alteração ou criação*/
    function carregaView() {
        if (location.hash == '#/v1/include-person') {
            $scope.acaoButton = 'Incluir Pessoa';
            $scope.classDivButton = 'col-sm-2';
            $scope.isUpdate = false;
            $scope.person = {gender : 'M'};
            $scope.showAddress = false;
            return 'Incluir Person';
        } else if (location.hash.search('#/v1/edit-person') > -1) {
            $scope.acaoButton = 'Alterar Pessoa';
            $scope.isUpdate = true;
            $scope.classDivButton = 'col-sm-0';
            PersonResource(Configuration.customHeaderV1).get({id : $routeParams.id}, function (data) {	
            	$scope.person = data.person;				
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
            });
            console.log( $scope.person);
            return 'Editar Pessoa';
        }
    }
    //nome da view de acordo coma requisição
    $scope.view = carregaView();		
    //carregamento das páginas
    $scope.isLoaded = false;		
    

    $scope.savePerson = function () {
        if(isNumber($scope.person.id) && location.hash.search('edit-person') > -1) {
            startLoad();
            PersonResource(Configuration.customHeaderV1).update($scope.person,function (data) {
                showMsgSuccess($scope, $timeout, 'Pessoa Alterada com Sucesso!!!');
                $scope.person = data.person;
                console.log(data);
                finishLoad();
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
                finishLoad();
            });
        }
        else {
            startLoad();
            console.log($scope.customHeader);
            PersonResource(Configuration.customHeaderV1).save($scope.person, function (data) {
            	console.log($scope.person);
                showMsgSuccess($scope, $timeout, 'Pessoa Inserida com Sucesso!!!');
                $scope.person = data.person;
                $scope.person.id = '';
                finishLoad();     
                
            },function (err) {
            	console.log(err)
                errCallbackService(err, $scope, $timeout);
                finishLoad();
            });				
        }
    }
    
    $scope.redirectToSearch =  function () {
    	location.href = '#/v1/person/';
    } 
    
    $scope.redirectToInclude = function () {
    	location.href = '#/v1/include-person';
    }

});


/**
 * Controller para view de pesquisade persons versão 2
 * ações de busca redirecionamento etc estão contidas neste bloco
 * */
app.controller('pesquisarPersonCtrlV2', function ($scope, PersonResource ,$timeout, Configuration) {
    $scope.view = 'Pesquisar Pessoa V2';
    //direciona para pagina de inclusão
    $scope.newPerson = function () {
        location.href = '#/v2/include-person';
    };
    $scope.editPerson = function (id) {
        location.href = '#/v2/edit-person/' + id;
    };
    
    //funçao para a busca de persons
    function getPerson(nome) {
        if (isNumber(nome)) {
            showMsgError($scope, $timeout, 'Digite uma cadeia válida de caracteres!!!');
            return;
        } else if (nome == null) {
            startLoad();
            $scope.persons = PersonResource(Configuration.customHeaderV2).getAll(function (data) {
                $scope.isLoaded = true;
                finishLoad();
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
                $scope.isLoaded = false;
                finishLoad();
            });							
        } else {
            startLoad();
            $scope.persons = PersonResource(Configuration.customHeaderV2).query({name : nome}, function (data) {
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

    $scope.removePerson = function (idParam, index) {
    	PersonResource(Configuration.customHeaderV2).remove({id : idParam}, function (data) {
            $scope.persons.splice(index, 1);
            showMsgSuccess($scope, $timeout, 'Pessoa excluida com sucesso!!!');
        }, function (err) {
            errCallbackService(err, $scope, $timeout);
        });
    }    
});	


/**
 * Controller view incluir Person versão dois
 * **/
app.controller('incluirAlterarPersonCtrlV2', function ($scope, PersonResource, buscaCepResource, $timeout, $routeParams, Configuration) {
    /*Função para carregar as funções e atributos da view de alteração ou criação*/
    function carregaView() {
        if (location.hash == '#/v2/include-person') {
            $scope.acaoButton = 'Incluir Pessoa';
            $scope.classDivButton = 'col-sm-2';
            $scope.isUpdate = false;
            $scope.person = {gender : 'M'};
            $scope.showAddress = false;
            return 'Incluir Pessoa Versão 2';
        } else if (location.hash.search('#/v2/edit-person') > -1) {
            $scope.acaoButton = 'Alterar Pessoa';
            $scope.isUpdate = true;
            $scope.classDivButton = 'col-sm-0';
            PersonResource(Configuration.customHeaderV2).get({id : $routeParams.id}, function (data) {	
            	$scope.person = data.person;				
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
            });
            return 'Editar Pessoa Versão 2';
        }
    }
    //nome da view de acordo coma requisição
    $scope.view = carregaView();		
    //carregamento das páginas
    $scope.isLoaded = false;		
    

    $scope.savePerson = function () {
        if(isNumber($scope.person.id) && location.hash.search('edit-person') > -1) {
            startLoad();
            PersonResource(Configuration.customHeaderV2).update($scope.person,function (data) {
                showMsgSuccess($scope, $timeout, 'Pessoa Alterada com Sucesso!!!');
                $scope.person = data.person;
                finishLoad();
            }, function (err) {
                errCallbackService(err, $scope, $timeout);
                finishLoad();
            });
        }
        else {
            startLoad();
            PersonResource(Configuration.customHeaderV2).save($scope.person, function (data) {
                showMsgSuccess($scope, $timeout, 'Pessoa Inserida com Sucesso!!!');
                $scope.person = data.person;
                $scope.person.id = '';
                finishLoad();     
                
            },function (err) {
                errCallbackService(err, $scope, $timeout);
                finishLoad();
            });				
        }
    }
    
    $scope.redirectToSearch =  function () {
    	location.href = '#/v2/person/';
    } 
    
    $scope.redirectToInclude = function () {
    	location.href = '#/v2/include-person';
    }
    //buscar endereço pelo cep
    $scope.searchAaddress = function () {
        if(typeof $scope.person == 'undefined' || typeof $scope.person.address == 'undefined') {
            showMsgError($scope, $timeout, 'Informe o CEP');
            angular.element('#cep').focus();
            return;
        }
        startLoad();;
        buscaCepResource.get({cep : $scope.person.address.postal_code, formato : 'json'}, function (data) {
            if(data.resultado_txt == 'sucesso - cep não encontrado') {
                finishLoad();
                showMsgError($scope, $timeout, 'Cep Inexisteste!!!');
                return;
            }
            $scope.person.address = {postal_code : $scope.person.address.postal_code, street : data.logradouro, district : data.bairro,  city : data.cidade, state : data.uf};
            finishLoad();
        },function (err) {
            finishLoad();
            showMsgError($scope, $timeout, 'Erro ao Buscar Cep!!!');
        });	
    }		
});

app.controller('homeCtrl', function ($scope) {
    $scope.message = 'Heloo Home';
});

angular.element(function() {
    angular.bootstrap(document, ['app']);
});