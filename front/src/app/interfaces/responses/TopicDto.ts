import { PostDto } from "./PostDto";

export interface TopicDto {
    id : number;
    title : string;
    description : string;
    posts : PostDto[];
}