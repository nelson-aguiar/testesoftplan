/**
* 
*/
/*Mensagens para operações realizadas com sucesso */
function showMsgSuccess($scope, $timeout, msg){
    $scope.mostraMsg = true;
    $scope.classMessage = 'alert alert-info default';
    $scope.message = msg;
    $timeout(function(){
        $scope.classMessage = 'alert alert-info fade';
        $timeout(function(){
            $scope.mostraMsg = false;				
        }, 300);
    }, 4000);	
}

/*Mensagens para operações com erros */
function showMsgError($scope, $timeout, msg){
    $scope.mostraMsg = true;
    $scope.classMessage = 'alert alert-danger default';
    $scope.message = msg;
    $timeout(function(){
        $scope.classMessage = 'alert alert-danger fade';
        $timeout(function(){
            $scope.mostraMsg = false;				
        }, 300);
    }, 4000);
}	

function errCallbackService(err, $scope, $timeout){
    if(err.status == -1){
        showMsgError($scope, $timeout, "Sem comunicação com o servidor");
        return
    }
    if(err.config.status == 500  && err.data.msg == 'undefined'){
        showMsgError($scope, $timeout, "Erro interno do servidor");
        return
    }
    //console.log(err);
    showMsgError($scope, $timeout, err.data.msg);
}

function isNumber(string){
    return (!isNaN(parseFloat(string)) && isFinite(string));
}

function startLoad(){
    angular.element('#modal').modal();
}

function finishLoad(){
    angular.element('#modal').modal('hide');
}	

function mvalor(v){
    v=v.replace(/\D/g,"");//Remove tudo o que não é digito
    v=v.replace(/(\d)(\d{8})$/,"$1.$2");//coloca o ponto dos milhões
    v=v.replace(/(\d)(\d{5})$/,"$1.$2");//coloca o ponto dos milhares 
    v=v.replace(/(\d)(\d{2})$/,"$1,$2");//coloca a virgula antes dos 2 últimos dígitos
    return v;
}

function stringToFloat(val){        
    val = val.replace(".", "").replace(",", ".");       
    return val;
}



