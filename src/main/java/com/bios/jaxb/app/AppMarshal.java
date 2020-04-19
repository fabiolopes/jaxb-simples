package com.bios.jaxb.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import com.bios.jaxb.Contato;
import com.bios.jaxb.Endereco;
import com.bios.jaxb.Telefone;

public class AppMarshal {

	public static void main(String[] args) {
		// Preenchimento end1 {
		Endereco end = new Endereco();
		end.setLogradouro("Rua Venancio Aires");
		end.setNumero(654);
		end.setComplemento("Ap.03A");
		end.setBairro("Centro");
		end.setCidade("Santa Maria");
		end.setCep("97050-100");
		end.setId(1);
		// }

		// Preenchimento telefones {
		Collection<Telefone> collection = new ArrayList<Telefone>();
		Telefone f1 = new Telefone();
		f1.setId(1);
		f1.setDdd(55);
		f1.setNumero(21210022);
		collection.add(f1);

		Telefone f2 = new Telefone();
		f2.setId(2);
		f2.setDdd(54);
		f2.setNumero(91910022);
		collection.add(f2);
		// }
		
		Contato contato = new Contato();
		contato.setId(1);
		contato.setNome("Ana Maria");
		contato.setEmail("ana.maria@gmail.com");
		contato.setEndereco(end);
		contato.setTelefones(collection);
		
		System.out.println(new AppMarshal().marshal(contato));
		new AppMarshal().mashalToFile(contato, "C:/java/contato-jaxb.xml");
		Contato c2 = (Contato) new AppMarshal().unmarshalFromFile(Contato.class, "C:/java/contato-jaxb.xml");
		System.out.println(c2.toString());
	}

	public String marshal(Object object) {
		final StringWriter out = new StringWriter();
		JAXBContext context = null;

		try {
			context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(
					javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(object, new StreamResult(out));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toString();
	}

	public String mashalToFile(Object object, String fileName) {
		final StringWriter out = new StringWriter();
		JAXBContext context = null;
		Marshaller marshaller = null;
		try {
			context = JAXBContext.newInstance(object.getClass());
			marshaller = context.createMarshaller();
			marshaller.setProperty(
					javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(object, new StreamResult(out));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Writer writer = null;

		try {
			writer = new FileWriter(fileName);
			marshaller.marshal(object, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return out.toString();

	}

	public Object unmarshalFromFile(Class<?> clazz, String fileXml) {
		JAXBContext context = null;
		Unmarshaller unmarshaller = null;

		try {
			context = JAXBContext.newInstance(clazz);
			unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new FileInputStream(fileXml));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
