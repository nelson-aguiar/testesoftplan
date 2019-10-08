/*global app*/
app.factory('PersonResource', function ($resource, Configuration) {
	return function (customHeader) {
		return $resource(Configuration().API+"/person/:id/:nome", {}, {
			
			'save' : {
				method : 'POST',
				headers : customHeader
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
	}
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

app.factory("autocompleteClienteResource", function ($resource, Configuration) {
   return $resource(Configuration.API+"/person-autocomplete/person-autocomplete/:nome", {}, {
        'search' : {
            method : 'GET', 
            isArray : true,
            params : {
                nome : '@nome'
            }
        }
    });
});