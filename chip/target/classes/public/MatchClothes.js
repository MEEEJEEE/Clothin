import React, { useState } from 'react';
import './MatchClothes.css';

const styles = ['Lovely', 'Chic', 'Casual', 'Formal'];

const categories = {
  Accessories: 'Accessories.png',
  Top: 'top.png',
  Bottoms: 'Bottoms.png',
  Shoes: 'shoes.png',
  Other: 'Other.png'
};

const MatchClothes = () => {
  const [style, setStyle] = useState(styles[0]);
  const [matchedClothes, setMatchedClothes] = useState({});

  const generateRecommendation = () => {
    const recommendation = {};
    Object.keys(categories).forEach(category => {
      recommendation[category] = `images/${style}-${categories[category]}`;
    });
    setMatchedClothes(recommendation);
  };

  return (
    <div>
      <div className="style-selector">
        <label>Select Style: </label>
        <select value={style} onChange={(e) => setStyle(e.target.value)}>
          {styles.map((styleOption) => (
            <option key={styleOption} value={styleOption}>{styleOption}</option>
          ))}
        </select>
      </div>
      <button onClick={generateRecommendation}>Get Fashion Recommendation</button>
      <div className="recommendation">
        {Object.keys(matchedClothes).map(category => (
          <div key={category} className="clothing-item">
            <img src={matchedClothes[category]} alt={`${style} ${category}`} />
            <p>{category}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MatchClothes;
