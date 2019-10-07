/*global app*/
app.factory('PersonResource', function ($resource) {
	return $resource("http://localhost:8080/api/teste-softplan/person/:id/:nome", {}, {

		'save' : {
			method : 'POST',
			headers : {'Content-Type':'application/json; charset=UTF-8'}
		},

		'update' : {
			method : 'PUT',
			headers : {'Content-Type':'application/json; charset=UTF-8'}
		},
		
		'get' : {
			method : 'GET',
			isArray : false,
			headers : {'Content-Type':'application/json; charset=UTF-8'},
			params : {
				id : '@id'
			}
		},

		'query' : {
			method : 'GET',
			isArray : true,
			headers : {'Content-Type':'application/json; charset=UTF-8'},
			params : {
				nome : '@nome'
			}
		},

		'getAll' : {
			method : 'GET',
			isArray : true
		},

		'remove' : {
			method : 'DELETE',
			params : {
				id : '@id'
			}
		}
	});
});

app.factory('buscaCepResource', function($resource) {
	return $resource("http://republicavirtual.com.br/web_cep.php", {}, {
		'get' : {
			method : 'GET',
			isArray : false,
			params : {
				cep : '@cep',
				formato : '@formato'
			}
		}
	});
})
;

app.factory("autocompleteClienteResource", function ($resource) {
   return $resource("http://localhost:8080/rest/cliente-autocomplete/cliente-autocomplete/:nome", {}, {
        'search' : {
            method : 'GET', 
            isArray : true,
            params : {
                nome : '@nome'
            }
        }
    });
});