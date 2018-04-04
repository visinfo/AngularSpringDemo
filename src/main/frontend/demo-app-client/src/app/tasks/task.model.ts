export class Task {
    public id: number;
    public title: string;
    public completed: boolean;
    public description: string;

    constructor(title: string, completed: boolean, description: string) {
        this.title = title;
        this.completed = completed;
        this.description = description;
    }

}