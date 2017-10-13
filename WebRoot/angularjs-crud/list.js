var app = angular.module('person-app', []);
app.controller('person-controller', function($scope, $http) {
    	$http.get("../test/person/query").success(
	    	function (response) {
		    	$scope.list = response;
		    }
	    );
    	$scope.toEdit_=function(index){
    		var person=$scope.list[index].columns; 
    		$scope.person=person;
    	};
    	$scope.delete_=function(index){
    		var id=$scope.list[index].columns.id;
    		$http.get("../test/person/delete?person.id="+id).success(
    				function(response){
    					alert(response);
    				}
    		)
    	};
});