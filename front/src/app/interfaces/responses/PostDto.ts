import { AuthorDto } from "./AuthorDto";

export interface PostDto {
    id : number,
    title : string,
    content : string,
    author : AuthorDto,
    created_at : string
}