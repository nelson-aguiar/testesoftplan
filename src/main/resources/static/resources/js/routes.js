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
		.when('/v1/include-person', {
			templateUrl : '/api/teste-softplan/views/person/person_alt_incluir.html',
			controller : 'incluirAlterarPersonCtrl',
		})
		.when('/v1/edit-person/:id', {
			templateUrl : '/api/teste-softplan/views/person/person_alt_incluir.html',
			controller : 'incluirAlterarPersonCtrl',
		})
		 .when('/v2/person/', {
            templateUrl : '/api/teste-softplan/views/person/personV2.html',
            controller : 'pesquisarPersonCtrlV2',
		})
		.when('/v2/include-person', {
			templateUrl : '/api/teste-softplan/views/person/person_alt_incluirV2.html',
			controller : 'incluirAlterarPersonCtrlV2',
		})
		.when('/v2/edit-person/:id', {
			templateUrl : '/api/teste-softplan/views/person/person_alt_incluirV2.html',
			controller : 'incluirAlterarPersonCtrlV2',
		})
		.otherwise({
			redirectTo : '/'
		});
	$locationProvider.hashPrefix('');
});