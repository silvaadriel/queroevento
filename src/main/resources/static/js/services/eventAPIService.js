angular.module("queroEventoApp").factory("eventAPI", function ($http, configs){

	var _postEvent = function (event){
		return $http.post(configs.baseUrl + configs.version + '/events', event);
	}
	
	var _putEvent = function (urlTitle, event){
		return $http.put(configs.baseUrl + configs.version + '/events/' + urlTitle, event);
	}
	
	var _putEventEstimate = function (id){
		return $http.put(configs.baseUrl + configs.version + '/events/' + id + '/estimate');
	}
	
	var _putEventStatus = function (id, event){
		return $http.put(configs.baseUrl + configs.version + '/events/' + id + '/status', event);
	}
	
	var _putEventCatalogStatus = function (id, event){
		return $http.put(configs.baseUrl + configs.version + '/events/' + id + '/status/catalog', event);
	}
	
	var _putEventTurbineType = function (id, event){
		return $http.put(configs.baseUrl + configs.version + '/events/' + id + '/turbine', event);
	}
	
	var _deleteEvent = function (id){
		return $http.delete(configs.baseUrl + configs.version + '/events/' + id);
	}
	
	var _getEventById = function (id){
		return $http.get(configs.baseUrl + '/events/' + id);
	}
	
	var _getEvents = function (){
		return $http.get(configs.baseUrl + '/events/');
	}
	
	var _getEventsPast = function (){
		return $http.get(configs.baseUrl + '/events/past');
	}
	
	var _getEventsByCategory = function (idCategory){
		return $http.get(configs.baseUrl + '/events/category/' + idCategory);
	}
	
	var _getEventsOrderByEstimate = function (){
		return $http.get(configs.baseUrl + '/events/estimate');
	}
	
	var _getEventsOrderByTurbineType = function (){
		return $http.get(configs.baseUrl + '/events/type/turbine');
	}
	
	var _getEventsOrderByPlanType = function (){
		return $http.get(configs.baseUrl + '/events/type/plan');
	}

	var _getEventByUrlTitle = function (urlTitle){
		return $http.get(configs.baseUrl + '/events/urltitle/' + urlTitle);
	}
	
    return {
    	postEvent: _postEvent,
    	putEvent: _putEvent,
    	putEventEstimate: _putEventEstimate,
    	putEventStatus: _putEventStatus,
    	putEventCatalogStatus: _putEventCatalogStatus,
    	putEventTurbineType: _putEventTurbineType,
    	deleteEvent: _deleteEvent,
    	getEventById: _getEventById,
    	getEvents: _getEvents,
    	getEventsPast: _getEventsPast,
    	getEventsByCategory: _getEventsByCategory,
    	getEventsOrderByEstimate: _getEventsOrderByEstimate,
    	getEventsOrderByTurbineType: _getEventsOrderByTurbineType,
    	getEventsOrderByPlanType: _getEventsOrderByPlanType,
    	getEventByUrlTitle: _getEventsOrderByPlanType
    };
	
});