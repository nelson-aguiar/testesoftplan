/*global app*/
app.config(function($routeProvider){    
		
	$routeProvider
		
		.when('/', {
			templateUrl : '/api/teste-softplan/views/home.html',
			controller : 'homeCtrl',
		})
		.when('/person/v1', {
			templateUrl : '/api/teste-softplan/views/person/person.html',
			controller : 'pesquisarPersonCtrl',
		})
        .when('person/v2', {
            templateUrl : '/api/teste-softplan/views/person/personV2.html',
            controller : 'incluirAlterarPersonCtrl',
		})
		.when('/include-person', {
			templateUrl : '/api/teste-softplan/views/person/person_alt_incluir.html',
			controller : 'incluirAlterarPersonCtrl',
		})
		.when('/edit-cliente/:id', {
			templateUrl : '/api/teste-softplan/views/cliente/person_alt_incluir.html',
			controller : 'incluirAlterarPersonCtrl',
		})
		.otherwise({
			redirectTo : '/'
		});
});