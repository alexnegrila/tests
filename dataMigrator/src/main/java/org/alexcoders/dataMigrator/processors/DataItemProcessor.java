package org.alexcoders.dataMigrator.processors;

import org.alexcoders.dataMigrator.dtos.DataDTO;
import org.alexcoders.dataMigrator.models.Data;
import org.springframework.batch.item.ItemProcessor;

public class DataItemProcessor implements ItemProcessor<DataDTO, Data>{
	
	@Override
    public Data process(final DataDTO data) throws Exception {
        
        final Data transformedData = new Data();
        transformedData.setId(data.getId());
        transformedData.setArticleId(data.getArticleId());
        //assure size
        String attribute = data.getAttribute();
        if (attribute.length()>=20) {
        	attribute = attribute.substring(0, 19);
        	System.out.println("Attribute was truncated to valid length of 22");
		}
		transformedData.setAttribute(attribute);
        
		transformedData.setValue(data.getValue());
        transformedData.setLanguage(data.getLanguage());
        transformedData.setType(data.getType());
        System.out.println("Converting (" + data + ") into (" + transformedData + ")");

        return transformedData;
    }

}