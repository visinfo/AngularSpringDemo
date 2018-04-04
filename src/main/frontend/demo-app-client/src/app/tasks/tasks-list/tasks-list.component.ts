import {Component, OnInit} from "@angular/core";
import {Response} from "@angular/http";

import {Task} from "../task.model";
import {TaskService} from "../task.service";

@Component({
    selector: 'app-tasks-list',
    templateUrl: './tasks-list.component.html',
    styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

    tasks: Task[] = [];

    constructor(private taskService: TaskService) {

    }

    ngOnInit() {
        // initial load of data
        this.taskService.getTasks()
            .subscribe(
                (tasks: any[]) => {
                    this.tasks = tasks
                },
                (error) => console.log(error)
            );
        // get notified when a new task has been added
        this.taskService.onTaskAdded.subscribe(
            (task: Task) => this.tasks.push(task)
        );
    }
onSubmit(form) {
         console.log(form.value.id);

        
         let task: Task = new Task(form.value.title,false, form.value.description);
    this.taskService.addTask(task)
      .subscribe(
          (newTask: Task) => {
              this.taskService.onTaskAdded.emit(newTask);
          }
      );

  }
deleteTask(id){

     this.taskService.deleteFood(id) .subscribe(
          
      );
     //this.ngOnInit(); 
    }
    getDueDateLabel(task: Task){
      return task.completed ? 'label-success' : 'label-primary';
    }

    onTaskChange(event, task) {
        this.taskService.saveTask(task,event.target.checked).subscribe();
    }
}
