package com.xywy.lucene.util;

import com.xywy.lucene.lucene.IKAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

@Component
public class IndexDataBase {
    //Lucene索引文件路径
    String indexPath = "E:\\doctor\\index";

    //定义分词器
    static Analyzer analyzer = new IKAnalyzer();
    /**
     * 封裝一个方法，用于将数据库中的数据解析为一个个关键字词存储到索引文件中
     * @param doc
     */
    public void write(Document doc){
        try {
            //索引库的存储目录
            Directory dir = FSDirectory.open(Paths.get(indexPath));

            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

            //传入目录和分词器
            IndexWriter writer = new IndexWriter(dir, iwc);
            //写入到目录文件中
            writer.addDocument(doc);
            //提交事务
            writer.commit();
            //关闭流
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
