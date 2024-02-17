package com.nfeconsult.service;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.nfeconsult.model.ClientModel;
import com.nfeconsult.model.NfeModel;
import com.nfeconsult.model.ProductModel;
import com.nfeconsult.model.StoreModel;

public class NfeService {
	private static Document doc;

	public static NfeModel readXML(NfeModel nfeModel, String path)
			throws ParserConfigurationException, SAXException, IOException {
		//teste
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(path);
		
		String code = getCode();
		if(code==null) return null;
		
		nfeModel.setCode(code);
		nfeModel.setStore(getStore(new StoreModel()));
		nfeModel.setClient(getClient(new ClientModel()));
		nfeModel.setProducts(getProducts());
		
		return nfeModel;
	}
	
	private static String getCode() {
		Node code = doc.getElementsByTagName("nNF").item(0);
		if(code!=null)
			return code.getTextContent();
		return null;
	}
	
	private static StoreModel getStore(StoreModel store) {
		Node emit = doc.getElementsByTagName("emit").item(0);
		if(emit!=null) {
			NodeList emitChild = emit.getChildNodes();
			Node name = getNode(emitChild, "xNome");
			Node cnpj = getNode(emitChild, "CNPJ");
			Node addresEmit = getNode(emitChild, "enderEmit");
			if(name!=null) store.setName(name.getTextContent());
			if(cnpj!=null) store.setCode(cnpj.getTextContent());
			if(addresEmit!=null) {
				NodeList addresEmitChild = addresEmit.getChildNodes();
				Node address = getNode(addresEmitChild, "xLgr");
				Node zone = getNode(addresEmitChild, "xBairro");
				Node city = getNode(addresEmitChild, "xMun");
				Node uf = getNode(addresEmitChild, "UF");
				if(address!=null)store.setAddress(address.getTextContent());
				if(zone!=null)store.setZone(zone.getTextContent());
				if(city!=null)store.setCity(city.getTextContent());
				if(uf!=null)store.setUf(uf.getTextContent());
			}
		}
		return store;
	}
	
	private static ClientModel getClient(ClientModel client) {
		Node dest = doc.getElementsByTagName("dest").item(0);
		if(dest!=null) {
			NodeList destChild = dest.getChildNodes();
			Node name = getNode(destChild, "xNome");
			Node cpf = getNode(destChild, "CPF");
			Node cnpj = getNode(destChild, "CNPJ");
			Node addresDest = getNode(destChild, "enderDest");
			if(name!=null) client.setName(name.getTextContent());
			if(cpf!=null) client.setCode(cpf.getTextContent());
			if(cnpj!=null) client.setCode(cnpj.getTextContent());
			if(addresDest!=null) {
				NodeList addresDestChild = addresDest.getChildNodes();
				Node address = getNode(addresDestChild, "xLgr");
				Node zone = getNode(addresDestChild, "xBairro");
				Node city = getNode(addresDestChild, "xMun");
				Node uf = getNode(addresDestChild, "UF");
				if(address!=null)client.setAddress(address.getTextContent());
				if(zone!=null)client.setZone(zone.getTextContent());
				if(city!=null)client.setCity(city.getTextContent());
				if(uf!=null)client.setUf(uf.getTextContent());
			}
		}
		return client;
	}
	
	private static ProductModel[] getProducts() {
		NodeList products = doc.getElementsByTagName("prod");
		ProductModel[] productList = new ProductModel[products.getLength()];
		for (int i = 0; i < products.getLength(); i++) {
			ProductModel product = new ProductModel();
			NodeList prodList = products.item(i).getChildNodes();
			Node desc = getNode(prodList, "xProd");
			Node qtd = getNode(prodList, "qCom");
			Node uVal = getNode(prodList, "vUnCom");
			Node tVal = getNode(prodList, "vProd");
			if(desc!=null) product.setDesc(desc.getTextContent());
			if(qtd!=null) product.setQtd(qtd.getTextContent());
			if(uVal!=null) product.setuVal(uVal.getTextContent());
			if(tVal!=null) product.settVal(tVal.getTextContent());
			productList[i] = product;		
		}
		return productList;
	}
	
	private static Node getNode(NodeList nodeList, String tag) { 
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeName().equals(tag)) return node;
		}
		return null;
	}
}
