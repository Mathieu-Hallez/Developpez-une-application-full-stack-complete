import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatOptions'
})
export class FormatOptionsPipe implements PipeTransform {

  transform(value: Array<any> | null, formatFn : (value : any, index : number, array : any[]) => MddSelectOption): Array<MddSelectOption> {
    if(value == null) {
      return [];
    }
    return value.map(formatFn);
  }

}

export type MddSelectOption = {
  title : string,
  value : string
}
