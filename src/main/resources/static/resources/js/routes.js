/*global app*/
app.config(function($routeProvider, $locationProvider){	
	$routeProvider		
		.when('/', {
			templateUrl : '/api/teste-softplan/views/home.html',
			controller : 'homeCtrl',
		})
		.when('/v1/person/', {
			templateUrl : '/api/teste-softplan/views/person/person.html',
			controller : 'pesquisarPersonCtrl',
		})
        .when('/v2/person/', {
            templateUrl : '/api/teste-softplan/views/person/personV2.html',
            controller : 'incluirAlterarPersonCtrl',
		})
		.when('/v1/include-person', {
			templateUrl : '/api/teste-softplan/views/person/person_alt_incluir.html',
			controller : 'incluirAlterarPersonCtrl',
		})
		.when('/v1/edit-cliente/:id', {
			templateUrl : '/api/teste-softplan/views/cliente/person_alt_incluir.html',
			controller : 'incluirAlterarPersonCtrl',
		})
		.otherwise({
			redirectTo : '/'
		});
	$locationProvider.hashPrefix('');
});