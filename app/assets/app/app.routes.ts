import { provideRouter, RouterConfig } from '@angular/router';

export const routes: RouterConfig = [
    // { path: 'crisis-center', component: CrisisCenterComponent },
    // { path: 'heroes', component: HeroListComponent },
    // { path: 'hero/:id', component: HeroDetailComponent }
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
