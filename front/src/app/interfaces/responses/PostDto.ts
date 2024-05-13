import { AuthorDto } from "./AuthorDto";
import { TopicDetailsDto } from "./TopicDetailsDto";

export interface PostDto {
    id : number,
    title : string,
    content : string,
    author : AuthorDto,
    topic : TopicDetailsDto,
    created_at : string
}