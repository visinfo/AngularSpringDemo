import "rxjs/Rx";
import {Task} from "./task.model";
import {EventEmitter, Injectable} from "@angular/core";
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class TaskService {

    onTaskAdded = new EventEmitter<Task>();
    options: RequestOptions;
	    headers: Headers;
	
    constructor(private http: Http) {
    
     this.headers = new Headers({ 'Content-Type': 'application/json', 
                                     'Accept': 'q=0.8;application/json;q=0.9' });
        this.options = new RequestOptions({ headers: this.headers });
    }

	 
    getTasks(){
        return this.http.get('/api/tasks')
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }
	 deleteFood(food_id:number){
          return this.http.delete('/api/task/'+food_id)
        
     }
    addTask(task: Task) {
        return this.http.post('/api/task', task)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }
	
	deleteTask(id: string) {
        
    		this.http.delete('/api/task');  
    		
    	return  null;	       
     }
   
       saveTask(task: Task, checked: boolean) {
        // we are updating the task to what the value of checked is
        task.completed = checked;
        return this.http.put('/api/task', task)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

}