/*global app*/
app.factory('PersonResource', function ($resource, Configuration) {
	return function (customHeader) {
		return $resource(Configuration.API+"/person/:id/:name", {}, {
			
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
				headers : customHeader,
				data : '',
				params : {
					id : '@id'
				}
			},
			
			'query' : {
				url : Configuration.API+"/person/search",
				method : 'GET',
				isArray : true,
				headers : customHeader,
				data : '', 
				params : {
					name : '@nome'
				}
			},
			
			'getAll' : {
				method : 'GET',
				isArray : true,
				headers : customHeader,
				data : ''
			},
			
			'remove' : {
				method : 'DELETE',
				params : {
					id : '@id'
				},
				headers : customHeader
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
   return $resource(Configuration.API+"/person/auto-complete/:nome", {}, {
        'search' : {
            method : 'GET', 
            isArray : true,
            params : {
                nome : '@nome'
            }
        }
    });
});