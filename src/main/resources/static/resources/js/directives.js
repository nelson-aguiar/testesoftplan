/*global app*/

app.directive('mask', function () {
	return {
		restrict : 'A',
		link : function (scope, element, attrs) {
			element.mask(attrs.mask);
		}
	};
});

app.directive('datepicker', function () {
	return {
		restrict : 'A',
		link : function (scope, element, attrs) {
			element.datepicker({
				changeMonth : true,
				changeYear : true,
		    	dayNames : ['Domingo', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Segunda'],
		    	dayNamesMin : ['Do', 'Se', 'Te', 'Qua', 'Qui', 'Sex', 'Sab'], 
		   		monthNames : ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
		   		dateFormat : 'dd/mm/yy'
		    });
		}
	}
});

app.directive('money', function () {
	return {
		restrict : 'A',
		link : function (scope, element, attrs) {
			element.on('keyup', function () {
				element.val(mvalor(element.val()));
			});
		}
	}
});

app.directive('showtab', function () {
    return {
    	restrict : 'A',
        link: function (scope, element, attrs) {
            element.click(function (e) {
                e.preventDefault();
                $(element).tab('show');
            });
        }
    };
});

app.directive('autocompleteCliente', function ($timeout, autocompleteClienteResource, $compile) {
	 return function(scope, element, attrs, ctrl) {
        element.autocomplete({
            minLength : 3,
            autofocus : true,
            source: function(serchTerm, response){
               autocompleteClienteResource.search({nome : serchTerm.term}).$promise.then(function(result){
                    response($.map(result, function(value, key){
                        return {
                            label : value.id+' -- '+value.nome,
                            value : value.nome,
                            id : value.id,
                            nome : value.nome,
                            endereco : value.endereco.endeRua+' '+value.endereco.endeNumero+
                            ' '+value.endereco.endeBairro+' ' +(value.endereco.complemento == null ? '' : value.endereco.complemento)+ value.endereco.cidade.descr+' '+value.endereco.cidade.estado.sigla,
                            telefoneRes : value.telRes,
                            telefoneCel : value.telCel
                        };
                    }));
                }, function(error){
                   response(error.data);
               });
            },                  
            select: function(event, ui) {
                scope.orcamento.cliente.id = ui.item.id;
                scope.orcamento.cliente.nome = ui.item.nome;
                scope.orcamento.cliente.endereco.endeRua = ui.item.endereco;
                scope.orcamento.cliente.telRes = ui.item.telefoneRes;
                scope.orcamento.cliente.telCel = ui.item.telefoneCel;
                $compile(scope);
                scope.$apply();
                event.preventDefault();
            }
        });
    };
});

app.directive('autocompleteProdServ', function ($timeout, autocompleteProdServResource, $compile) {
	 return function(scope, element, attrs, ctrl) {
        element.autocomplete({
            minLength : 3,
            autofocus : true,
            source: function(serchTerm, response){
               autocompleteProdServResource.search({descr : serchTerm.term}).$promise.then(function(result){
                    response($.map(result, function(value, key){
                        return {
                            label : value.id+' -- '+value.descr,
                            value : value.descr,
                            id : value.id,
                            descr : value.descr,
                            tipo  : value.tipo,
                            unMedida  : value.unMedida,
                            valor : value.valSugerido
                        };
                    }));
                }, function(error){
                   console.log(error);
                   response(error.data);
               });
            },                  
            select: function(event, ui) {
                scope.item.descr =  ui.item.descr;
                scope.itemOrcamentoAux.produtoServico.id = ui.item.id
                scope.itemOrcamentoAux.produtoServico.descr = ui.item.descr;
                scope.itemOrcamentoAux.produtoServico.tipo = ui.item.tipo;
                scope.itemOrcamentoAux.produtoServico.unMedida = ui.item.unMedida;
                scope.itemOrcamentoAux.produtoServico.valSugerido = ui.item.valor;
                $compile(scope);
                scope.$apply();
                event.preventDefault();
            }
        });
    };
});