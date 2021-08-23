		SELECT a.cityCode  , a.gunCode , a.dongCode, a.cityN , a.gunN , a.dongN , IFNULL(tem.gunCount,0) as gunCount
       
        FROM address_code AS a 
        LEFT JOIN  
        
        (
			select tem.* from (
				select a.cityCode , a.gunCode ,a.gunN , count(a.gunCode) as gunCount
				from web_member AS a 
				group by a.cityCode ,a.gunCode
				having 1=1
				
				 and a.cityCode = '31'
		   
			) as tem
        ) as tem
        
        
		ON a.cityCode = tem.cityCode
        and  a.gunCode = tem.gunCode

        where 1=1 
		and a.cityCode = '31'
        
		GROUP BY a.cityCode ,a.gunCode 
        order by gunN asc
   