package presentation;

import Exceptions.NoSuchIdException;
import Exceptions.UnderStockException;
import Utility.BillCompute;
import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

/**
 * Clasa care controleaza intreaga aplicatie. Aici se iau decizii la apasarea unor butoane si la setarea unor field-uri
 */
public class Controller {

    View view;
    ClientView clientView;
    ProductView productView;
    OrderView orderView;
    TableView tableView;
    ClientBLL clientBLL;
    ProductBLL productBLL;
    OrderBLL orderBLL;

    /**
     * Constructorul clasei, in care se adauga actionListener pe toate butoanele si se instantiaza view-urile si logicile
     *
     * @param view fereastra principala
     */
    public Controller(View view) {
        this.view = view;
        view.addGoListener(new GoListener());
        clientView = new ClientView();
        clientView.addInsertListener(new ClientInsertListener());
        clientView.addUpdateListener(new ClientUpdateListener());
        clientView.addDeleteListener(new ClientDeleteListener());
        clientView.addViewAllListener(new ClientViewAllListener());
        productView = new ProductView();
        productView.addInsertListener(new ProductInsertListener());
        productView.addUpdateListener(new ProductUpdateListener());
        productView.addDeleteListener(new ProductDeleteListener());
        productView.addViewAllListener(new ProductViewAllListener());
        orderView = new OrderView();
        orderView.addInsertListener(new OrderInsertListener());
        orderView.addUpdateListener(new OrderUpdateListener());
        orderView.addDeleteListener(new OrderDeleteListener());
        orderView.addViewAllListener(new OrderViewAllListener());

        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
    }

    /**
     * Alege care fereastra se deschide in functie de dorinta utilizatorului
     */
    class GoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String comboInput = view.getCombo();
                if (comboInput.equals("Client")) {
                    clientView.setVisible(true);
                }
                if (comboInput.equals("Product")) {
                    productView.setVisible(true);
                }
                if (comboInput.equals("Order")) {
                    orderView.setVisible(true);
                }
            } catch (Exception ex) {
                view.showError("An error has occurred");
            }
        }
    }

    /**
     * Insereaza un client in baza de date
     */
    class ClientInsertListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String name = clientView.getNameInput();
                String address = clientView.getAddressInput();
                String email = clientView.getEmailInput();
                clientBLL.insert(new Client(name, address, email));
                clientView.showInfo("Client added");

            } catch (NumberFormatException nfex) {
                clientView.showError("Wrong input. Please retry.");
            } catch (IllegalArgumentException iae) {
                clientView.showError(iae.getMessage());
            } catch (Exception ex) {
                clientView.showError("An error has occurred");
            }
        }
    }

    /**
     * Editeaza campurile unui client din baza de date
     */
    class ClientUpdateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(clientView.getIdInput());
                Client client = clientBLL.findById(id);
                String field = "", value = "";
                if (!clientView.getNameInput().equals("")) {
                    field = "name";
                    value = clientView.getNameInput();
                } else if (!clientView.getAddressInput().equals("")) {
                    field = "address";
                    value = clientView.getAddressInput();
                } else if (!clientView.getEmailInput().equals("")) {
                    field = "email";
                    value = clientView.getEmailInput();
                } else
                    throw new IllegalArgumentException("Please complete the fields to update.");
                clientBLL.update(client, field, value);

                clientView.showInfo("Client updated");
            } catch (NoSuchIdException | IllegalArgumentException iae) {
                clientView.showError(iae.getMessage());
            } catch (Exception ex) {
                clientView.showError("An error has occurred");
            }
        }
    }

    /**
     * Sterge un client din baza de date
     */
    class ClientDeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(clientView.getIdInput());
                clientBLL.findById(id);
                clientBLL.delete(id);
                clientView.showInfo("Client deleted");
            } catch (NumberFormatException nfex) {
                clientView.showError("Wrong input. Please retry.");
            } catch (NoSuchIdException | NoSuchElementException nex) {
                clientView.showError(nex.getMessage());
            } catch (Exception ex) {
                clientView.showError("An error has occurred");
            }
        }
    }

    /**
     * Afiseaza intr-un JTable toti clientii din baza de date
     */
    class ClientViewAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                tableView = new TableView(new Client(), clientBLL);
            } catch (NoSuchElementException nex) {
                view.showError(nex.getMessage());
            } catch (Exception ex) {
                clientView.showError("An error has occurred");
            }
        }
    }

    /**
     * Insereaza un produs in baza de date
     */
    class ProductInsertListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String name = productView.getNameInput();
                int price = Integer.parseInt(productView.getPriceInput());
                int stock = Integer.parseInt(productView.getStockInput());
                productBLL.insert(new Product(name, price, stock));

                productView.showInfo("Product added");
            } catch (NumberFormatException nfex) {
                productView.showError("Wrong input. Please retry.");
            } catch (IllegalArgumentException iae) {
                productView.showError(iae.getMessage());
            } catch (Exception ex) {
                productView.showError("An error has occurred");
            }
        }
    }

    /**
     * Editeaza un produs din baza de date
     */
    class ProductUpdateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(productView.getIdInput());
                Product product = productBLL.findById(id);
                String field = "", value = "";
                if (product != null) {
                    if (!productView.getNameInput().equals("")) {
                        field = "name";
                        value = productView.getNameInput();
                    } else if (!productView.getPriceInput().equals("")) {
                        field = "price";
                        value = productView.getPriceInput();
                    } else if (!productView.getStockInput().equals("")) {
                        field = "stock";
                        value = productView.getStockInput();
                    } else
                        throw new IllegalArgumentException("Please complete the fields to update.");
                    productBLL.update(product, field, value);
                }
                productView.showInfo("Product updated");
            } catch (NumberFormatException nfex) {
                productView.showError("Wrong input. Please retry.");
            } catch (NoSuchIdException | IllegalArgumentException | NoSuchElementException iae) {
                productView.showError(iae.getMessage());
            } catch (Exception ex) {
                productView.showError("An error has occurred");
            }
        }
    }

    /**
     * Sterge un produs din baza de date
     */
    class ProductDeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(productView.getIdInput());
                productBLL.findById(id);
                productBLL.delete(id);

                productView.showInfo("Product deleted");
            } catch (NumberFormatException nfex) {
                productView.showError("Wrong input. Please retry.");
            } catch (NoSuchIdException | NoSuchElementException nex) {
                productView.showError(nex.getMessage());
            } catch (Exception ex) {
                productView.showError("An error has occurred");
            }
        }
    }

    /**
     * Afiseaza intr-un JTable toate produsele din baza de date
     */
    class ProductViewAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                tableView = new TableView(new Product(), productBLL);
            } catch (NoSuchElementException nex) {
                productView.showError(nex.getMessage());
            } catch (Exception ex) {
                productView.showError("An error has occurred");
            }
        }
    }

    /**
     * Insereaza o comanda in baza de date, actualizeaza stocul si creaza factura
     */
    class OrderInsertListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int clientId = Integer.parseInt(orderView.getClientIdInput());
                int productId = Integer.parseInt(orderView.getProductIdInput());
                int quantity = Integer.parseInt(orderView.getQuantityInput());
                Client client = clientBLL.findById(clientId);
                Product product = productBLL.findById(productId);
                if (product.getStock() < quantity) {
                    throw new UnderStockException("Insufficient stock!");
                }
                orderBLL.insert(new Orders(clientId, productId, quantity));
                productBLL.update(product, "stock", String.valueOf(product.getStock() - quantity));
                BillCompute.createBill(client, product, quantity);
                clientView.showInfo("Order added");
            } catch (NumberFormatException nfex) {
                orderView.showError("Wrong input. Please retry.");
            } catch (UnderStockException | IllegalArgumentException | NoSuchIdException | NoSuchElementException nex) {
                orderView.showError(nex.getMessage());
            } catch (Exception ex) {
                orderView.showError("An error has occurred");
            }
        }
    }

    /**
     * Editeaza o comanda din baza de date
     */
    class OrderUpdateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(orderView.getIdInput());
                Orders order = orderBLL.findById(id);
                String field = "", value = "";
                if (!orderView.getQuantityInput().equals("")) {
                    field = "quantity";
                    value = orderView.getQuantityInput();
                } else
                    throw new IllegalArgumentException("Please complete the fields to update.");
                orderBLL.update(order, field, value);

                orderView.showInfo("Order updated");
            } catch (NumberFormatException nfex) {
                orderView.showError("Wrong input. Please retry.");
            } catch (NoSuchIdException | IllegalArgumentException | NoSuchElementException iae) {
                orderView.showError(iae.getMessage());
            } catch (Exception ex) {
                orderView.showError("An error has occurred");
            }
        }
    }

    /**
     * Sterge o comanda din baza de date
     */
    class OrderDeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(orderView.getIdInput());
                orderBLL.findById(id);
                orderBLL.delete(id);

                clientView.showInfo("Order deleted");
            } catch (NumberFormatException nfex) {
                orderView.showError("Wrong input. Please retry.");
            } catch (NoSuchIdException | IllegalArgumentException | NoSuchElementException nex) {
                orderView.showError(nex.getMessage());
            } catch (Exception ex) {
                orderView.showError("An error has occurred");
            }
        }
    }

    /**
     * Afiseaza intr-un JTable toate comenzile din baza de date
     */
    class OrderViewAllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                tableView = new TableView(new Orders(), orderBLL);
            } catch (NoSuchElementException nex) {
                orderView.showError(nex.getMessage());
            } catch (Exception ex) {
                orderView.showError("An error has occurred");
            }
        }
    }

}
