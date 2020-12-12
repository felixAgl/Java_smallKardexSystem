export const DynamicAsideMenuConfig = {
  items: [
    {
      title: 'Dashboard',
      root: true,
      icon: 'flaticon2-architecture-and-city',
      svg: './assets/media/svg/icons/Design/Layers.svg',
      page: '/dashboard',
      translate: 'MENU.DASHBOARD',
      bullet: 'dot',
    },    
    { section: 'Applications' },
    {
      title: 'eCommerce',
      bullet: 'dot',
      icon: 'flaticon2-list-2',
      svg: './assets/media/svg/icons/Shopping/Cart3.svg',
      root: true,
      permission: 'accessToECommerceModule',
      page: '/ecommerce',
      submenu: [
        {
          title: 'Home',
          page: '/ecommerce/home'
        },
        {
          title: 'Consults',
          page: '/ecommerce/consults'
        },        
        {
          title: 'Stock',
          page: '/ecommerce/stock'
        },        
        {
          title: 'Sale',
          page: '/ecommerce/sale'
        },
      ]
    },    
  ]
};
