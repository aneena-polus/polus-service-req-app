import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter, withHashLocation } from '@angular/router';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppComponent } from './app/app.component';
import { routes } from './app/app.routes';

bootstrapApplication(AppComponent, {
    providers: [...appConfig.providers,provideHttpClient(),
                provideRouter( routes, withHashLocation() ),
                provideAnimationsAsync(),
                provideAnimationsAsync()
                ],
}).catch((err) => console.error(err));
