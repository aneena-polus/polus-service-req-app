import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate',
  standalone: true
})

export class TruncatePipe implements PipeTransform {

  	public transform( value: string, limit: number ): string {
		if( !value ) {
			return '';
		}
		return value.length > limit ? value.substring(0, limit) + '...' : value;
  	}

}
