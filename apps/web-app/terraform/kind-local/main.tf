locals {
  app_name = "web-app"
}

resource "kubernetes_config_map" "web-app" {
  metadata {
    name      = "${local.app_name}-terraform"
    namespace = var.namespace
  }
  data = {
    BOOK_SERVICE_URL = "book-service"
    ORDER_SERVICE_URL = "order-service"
  }
}

