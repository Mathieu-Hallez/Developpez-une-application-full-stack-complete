import { AuthorDto } from "./AuthorDto";

export interface CommentDto {
    id : number;
    author : AuthorDto;
    content : string;
    created_at : string;
}